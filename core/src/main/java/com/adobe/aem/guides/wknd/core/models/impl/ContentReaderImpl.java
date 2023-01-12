package com.adobe.aem.guides.wknd.core.models.impl;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.Optional;

import com.adobe.aem.guides.wknd.core.models.ContentReader;
import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;


@Model(adaptables ={Resource.class},adapters={ContentReader.class},
                    resourceType={ContentReaderImpl.RESOURCE_TYPE},
                    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)


public class ContentReaderImpl implements ContentReader {
    protected static final String RESOURCE_TYPE = "rightpoint/components/contentreader";
    
   
    
    @Inject
    ResourceResolver resourceResolver;
    private Optional<ContentFragment> contentFragment;

    @ValueMapValue
    private String cfPath;

    @PostConstruct
    public void init(){
        Resource cfResource = resourceResolver.getResource(cfPath);
         contentFragment = Optional.ofNullable(cfResource.adaptTo(ContentFragment.class));
        
    } 


    @Override
    public String getTitle() {
        
        return contentFragment.map(cf->cf.getElement("title")).map(ContentElement::getContent).orElse(StringUtils.EMPTY);
    }

    @Override
    public String getCardImage() {
       
        return contentFragment.map(cf->cf.getElement("cardImage")).map(ContentElement::getContent).orElse(StringUtils.EMPTY);
    }

    @Override
    public String getDate() {

        return contentFragment.map(cf->cf.getElement("date")).map(ContentElement::getContent).orElse(StringUtils.EMPTY);
    }

    @Override
    public String getDescription() {

        return contentFragment.map(cf->cf.getElement("description")).map(ContentElement::getContent).orElse(StringUtils.EMPTY);    }


  
    
}

