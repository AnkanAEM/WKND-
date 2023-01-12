package com.adobe.aem.guides.wknd.core.models.impl;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.adobe.aem.guides.wknd.core.models.ContentOne;
import com.adobe.aem.guides.wknd.core.models.ContentReaderOne;
import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;


@Model(adaptables ={Resource.class},adapters={ContentReaderOne.class},
                    resourceType={ContentReaderImplOne.RESOURCE_TYPE},
                    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)


public class ContentReaderImplOne implements ContentReaderOne {
    protected static final String RESOURCE_TYPE = "wknd/components/contentreader";
    
   
    
    @Inject
    ResourceResolver resourceResolver;
    private Optional<ContentFragment> contentFragment;

    @ValueMapValue
    private List<String> contentData;

    private List<ContentOne> content;

    @PostConstruct
    public void init(){
        content = new ArrayList<>();
        List<Resource> cfResource = new ArrayList<>();

        contentData.forEach(
           (temp) -> {cfResource.add(resourceResolver.getResource(temp));} );
        
        // Resource cfResource = resourceResolver.getResource(cfPath);
        //contentFragment = Optional.ofNullable(cfResource.adaptTo(ContentFragment.class));
        
        Iterator<Resource> it =cfResource.iterator();

        while(it.hasNext()){
            contentFragment = Optional.ofNullable(it.next().adaptTo(ContentFragment.class));
            ContentOne card = new ContentOne();
            card.setTitle(contentFragment.map(cf->cf.getElement("title")).map(ContentElement::getContent).orElse(StringUtils.EMPTY));
            card.setDescription(contentFragment.map(cf->cf.getElement("description")).map(ContentElement::getContent).orElse(StringUtils.EMPTY));
            card.setCardImage(contentFragment.map(cf->cf.getElement("cardImage")).map(ContentElement::getContent).orElse(StringUtils.EMPTY));
            card.setDate(contentFragment.map(cf->cf.getElement("date")).map(ContentElement::getContent).orElse(StringUtils.EMPTY));
            content.add(card); 
        }
    } 


    @Override
    public List<ContentOne> getContent() {
        return content;
    }


  
    
}

