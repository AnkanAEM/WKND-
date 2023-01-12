package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import com.adobe.aem.guides.wknd.core.OSGiConfigs.SimpleConfiguration;
import com.adobe.aem.guides.wknd.core.Services.ConfigurationConsumer;

@Component(service = {Servlet.class})
@SlingServletResourceTypes(
    resourceTypes = "wknd/servlet",
    methods = HttpConstants.METHOD_POST,
    extensions = "txt",
    selectors = {"dam1","DAM1","Dam1"}
)

public class TestServlet extends SlingAllMethodsServlet{
    @Reference
    private ConfigurationConsumer configurationConsumer;
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter writer = response.getWriter();
            writer.println("This Servlet is hit ");
            String User = request.getRemoteUser();
            writer.println(User);
            writer.println(configurationConsumer.PrintValues());
            writer.println(request.getLocalPort());
            Session session = request.getResourceResolver().adaptTo(Session.class);
            // String paramVal = request.getParameter("param1");
            // String paramVal2 = request.getRequestParameter("param1").getName();
            // InputStream stream = request.getRequestParameter("file").getInputStream();
            request.getRequestParameterList().forEach( p->writer.println(p.getName()));
            Iterable<Part> parts = request.getParts();
            parts.forEach(p->writer.println(p.getName()));
            
            // try {
            //     Node node= session.getRootNode();
            //     node.getPath();
            //     node.addNode("NodeUsingSession");
            //     session.save();
            // } catch (RepositoryException e) {
                
            //     e.printStackTrace();
            // }
           
            
    }
}
