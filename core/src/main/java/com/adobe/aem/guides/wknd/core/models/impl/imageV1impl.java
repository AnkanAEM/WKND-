package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.imageV1;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;


@Model
        (adaptables = { SlingHttpServletRequest.class }, 
        adapters = { imageV1.class }, 
        resourceType = {imageV1impl.RESOURCE_TYPE}, 
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class imageV1impl implements imageV1 {

    protected static final String RESOURCE_TYPE = "wknd/components/imagev1";
   

    @SlingObject
    private Resource resource;

    @SlingObject
    private SlingHttpServletRequest request;

    @ValueMapValue
    String title;

    @ValueMapValue
    String description;
    
    @ValueMapValue
    String image;
    
    @PostConstruct
    public void init(){
        // title = resource.getValueMap().get("title", String.class);
        // description = resource.getValueMap().get("description", String.class);
        // image = resource.getValueMap().get("image", String.class);



    }


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getImage() {
        return image;
    }


    
}
