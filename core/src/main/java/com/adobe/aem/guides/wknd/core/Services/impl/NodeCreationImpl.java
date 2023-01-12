package com.adobe.aem.guides.wknd.core.Services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.guides.wknd.core.Services.NodeCreation;
import com.adobe.aem.guides.wknd.core.utils.ResolverUtils;

@Component(service = NodeCreation.class, immediate = true)

public class NodeCreationImpl implements NodeCreation {
   
    @Reference
    private ResourceResolverFactory resourceResolverFactory;
    
    private final static String SERVICE_USER = "wkndServiceUserB";
    private final static String ROOT_PATH = "/content/dam/wknd";


    
    @Override
    public void createNode(String path, String nodeName) {
    ResolverUtils resolverUtils = new ResolverUtils();    
    ResourceResolver resourceResolver = resolverUtils.getNewResolver(SERVICE_USER, resourceResolverFactory);
    Resource resource = resourceResolver.getResource(ROOT_PATH);
    Node rootNode = resource.adaptTo(Node.class);
    try {
        if(rootNode.hasNode(nodeName)){
            rootNode.getNode(nodeName).setProperty("Name", "Updated Name Value");
        }else{
            Node newNode = rootNode.addNode(nodeName,"nt:unstructured");
            newNode.setProperty("Name","Anakn ");
        }
        
        resourceResolver.commit();

    } catch (RepositoryException | PersistenceException e) {
        e.printStackTrace();
    }
    
        
    }
    
}
