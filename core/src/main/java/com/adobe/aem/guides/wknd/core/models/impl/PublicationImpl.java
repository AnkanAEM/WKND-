package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.adobe.aem.guides.wknd.core.models.BookItem;
import com.adobe.aem.guides.wknd.core.models.Publication;

import com.adobe.cq.dam.cfm.ContentElement;
//import org.apache.sling.models.factory.ModelFactory;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.day.cq.wcm.designimporter.parser.taghandlers.factory.TitleComponentTagHandlerFactory;

import java.util.Optional;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
//import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.commons.lang3.StringUtils;
//import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
//import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { Publication.class }, resourceType = {
        PublicationImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class PublicationImpl implements Publication{

    protected static final String RESOURCE_TYPE = "wknd/components/publication";

    @SlingObject
    private Resource resource;

    @Self
    private SlingHttpServletRequest request;
    

    @ValueMapValue
    private String title;

   

    @ValueMapValue
    private List<BookItem> books;


    @PostConstruct
    public void init(){
         books =new ArrayList<>();

        Resource res = resource.getChild("books");

        if(null != res && res.hasChildren()) {

            Iterator<Resource> cards = res.listChildren();

            while(cards.hasNext()) {

                Resource card = cards.next();

                BookItem book = new BookItem();

                book.setTitle2(card.getValueMap().get("title2", String.class));

                book.setDescription(card.getValueMap().get("description", String.class));

                book.setDate(card.getValueMap().get("date", String.class));

                books.add(book);

            }

        }
    }


    @Override
    public String getTitle() {
        return title;
    }


    @Override
    public List<BookItem> getBooks() {
        return books;
    }

}

