package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.xml.ws.Response;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.Services.NodeCreation;


@Component(service = {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes="wknd/components/page",
        // resourceTypes="core/wcm/components/page/v2/page",
        methods=HttpConstants.METHOD_GET,
        extensions="txt",
        selectors = {"node", "Node", "NODE"}
        )
@ServiceDescription("Simple Demo Servlet")

public class NodeServiceConsumerServlet extends SlingAllMethodsServlet {

    @Reference
    private NodeCreation nodeCreation;

    public static Logger log = LoggerFactory.getLogger(NodeServiceConsumerServlet.class);
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response){
        try {
            String path = request.getParameter("path");
            String nodeName = request.getParameter("nodeName");
            response.getWriter().write("Path is "+path+" Node Name is "+nodeName);
            nodeCreation.createNode(path, nodeName);
            response.getWriter().write("Node Creation is Done");
            log.debug("Node is created Successfully");
        } catch (Exception e) {
            log.debug("Exception Happened Here"+e);
        }
      

        
    }
}
