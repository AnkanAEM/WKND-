package com.adobe.aem.guides.wknd.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResolverUtils {
    public static Logger log = LoggerFactory.getLogger(ResolverUtils.class);
    public ResourceResolver getNewResolver(String serviceUser, ResourceResolverFactory resolverFactory){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(ResourceResolverFactory.SUBSERVICE,serviceUser);
        ResourceResolver serviceResourceResolver = null;
        try {
            serviceResourceResolver = resolverFactory.getServiceResourceResolver(paramMap);
            
        } catch (Exception e) {
            log.debug("Error is :",e);
        }
        return serviceResourceResolver;
    }
}
