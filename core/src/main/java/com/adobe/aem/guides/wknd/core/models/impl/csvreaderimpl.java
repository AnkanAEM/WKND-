package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.csvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;



import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { csvreader.class }, resourceType = {
    csvreaderimpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class csvreaderimpl implements csvreader {
    protected static final String RESOURCE_TYPE = "wknd/components/csvreader";
    String[] file ;
    List<String[]> csv = new ArrayList<String[]>();

    @SlingObject
    private Resource resource;

    @Self
    private SlingHttpServletRequest request;

    @ValueMapValue
    private String path;

    @PostConstruct
    public void init(){
        String line;

        try {
           BufferedReader reader = new BufferedReader(new FileReader(path));
            while((line=reader.readLine())!=null) {
            file = line.split(",");
            csv.add(file);
            
            }
            reader.close();
        }
        
        catch(Exception e){
            
        }
        
    }

    @Override
    public List<String[]> getcsv() {
        // TODO Auto-generated method stub
        return csv;
    }
    
}
