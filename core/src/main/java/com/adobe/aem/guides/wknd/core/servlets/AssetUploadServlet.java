package com.adobe.aem.guides.wknd.core.servlets;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.jcr.Binary;
import javax.jcr.Session;
import javax.jcr.ValueFactory;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import javax.xml.ws.Response;

import org.osgi.framework.Constants;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.request.RequestParameterMap;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.guides.wknd.core.models.csvreader;
import com.day.cq.dam.api.AssetManager;
import com.google.common.primitives.Bytes;
import com.opencsv.CSVReader;

@Component(service = { Servlet.class }, property = { Constants.SERVICE_DESCRIPTION + "=File Upload Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.paths=" + "/bin/uploaddamasset2" })

public class AssetUploadServlet extends SlingAllMethodsServlet {
    private static final long serialVersionUID = 1L;
    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(ResourceResolverFactory.SUBSERVICE, "wkndServiceUser");
        try {
            ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(paramMap);
            Session session = resourceResolver.adaptTo(Session.class);
            Session session2 = request.getResourceResolver().adaptTo(Session.class);
            PrintWriter Writer = response.getWriter();
            Writer.write("Servlet is Hit by the Submit Action");

            // Map<String, RequestParameter[]> requestParameterMap = request.getRequestParameterMap();
            // Set<Entry<String, RequestParameter[]>> entryset = requestParameterMap.entrySet();

            // for (Entry<String, RequestParameter[]> paramPair : requestParameterMap.entrySet()) {
                // String key = paramPair.getKey();
                // if (key.equals("inputFile")) {
                    // RequestParameter[] values = paramPair.getValue();
                    // int length = values.length;
                    // Writer.println(length);
                    
                    // RequestParameter paramVal = values[0];
                    // Writer.println(values[0].getInputStream().toString());
                    Part filePart = request.getPart("inputFile");
                    try {
                        String fileName = filePart.getSubmittedFileName();
                        Writer.println(fileName);
                        InputStream inputStream = filePart.getInputStream();
                        if (null != inputStream) {
                            byte[] bytes = inputStream.readAllBytes(); 
                            // IOUtils.toByteArray(inputStream);
                            
                            // AssetManager assetMg = resourceResolver.adaptTo(AssetManager.class);
                            AssetManager assetMg = request.getResourceResolver().adaptTo(AssetManager.class);
                            final ValueFactory valueFactory = session2.getValueFactory();
                            final Binary binary = valueFactory.createBinary(inputStream);
                            assetMg.createOrReplaceAsset("/content/dam/wknd/custom/"+"BB.csv", binary , "text/csv" , false);

                            // HSSFWorkbook wb = new HSSFWorkbook(inputStream);
                            // HSSFSheet sheet = wb.getSheetAt(0);
                            // // FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
                            // int iterationCount = 0;
                            // for (Row row : sheet) {
                            //     iterationCount++;
                            //     for (Cell cell : row) {
                            //         if(iterationCount==1){
                            //             String val = cell.getStringCellValue();
                            //             System.out.println(val);
                            //             Writer.write("The Value in Workbook is"+val);
                            //         }
                            //     }
                            // }
                            // CSVReader reader = new CSVReader(new InputStreamReader(inputStream, "UTF-8"));
                            // List<String[]> r = reader.readAll();
                            // while(reader.readNext()!=null){
                            //     String[] next = reader.readNext();
                            //     for(String val:next){
                            //         Writer.println(key);
                            //     }
                            // }
                            // r.forEach(x -> Writer.write(Arrays.toString(x)));
                            // BufferedReader csvReader = null;
                            // List<List<String>> csvList = new ArrayList<List<String>>();
                            // String csvRecord = null;

                            // csvReader = new BufferedReader(new InputStreamReader(csvInput, "UTF-8"));
                            // while ((csvRecord = csvReader.readLine()) != null) {
                            //     csvList.add(parseCsvRecord(csvRecord, ","));
                            // }
                            session.save();
                   
                            resourceResolver.commit();
                            
                            // File file = new File("myExcel.xls");
                            // FileUtils.copyInputStreamToFile(inputStream, file);
                            // Workbook workbook = Workbook.getWorkbook(file);
                            // String content = workbook.getSheet(0).getCell(0,0).getContents();
                            // Writer.write("The Value in Workbook is"+content);
                            
                        }

                    } catch (Exception exception) {
                        System.out.println(exception);
                    }
                // }
            // }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
