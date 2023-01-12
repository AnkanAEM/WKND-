package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import javax.inject.Inject;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.wknd.core.dto.FeaturedArticle;
import com.adobe.aem.guides.wknd.core.models.FeaturedArticles;
import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;


@Model( adaptables ={Resource.class},
        adapters = {FeaturedArticles.class},
        resourceType={FeaturedArticlesImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)



public class FeaturedArticlesImpl implements FeaturedArticles {

    protected static final String RESOURCE_TYPE = "wknd/components/featuredarticles";

    @Inject
    ResourceResolver resourceResolver;
    private Optional<ContentFragment> contentFragment;
    
    @ValueMapValue
    private List<String> articleFragments;

    private List<FeaturedArticle> articles;

    @PostConstruct
    void init (){
        articles = new ArrayList<>();
        List<Resource> cfResource = new ArrayList<>();
        articleFragments.forEach(
        (temp) -> {cfResource.add(resourceResolver.getResource(temp));} );
        
        Iterator<Resource> it = cfResource.iterator();
        while(it.hasNext()){
            contentFragment = Optional.ofNullable(it.next().adaptTo(ContentFragment.class));
            FeaturedArticle card = new FeaturedArticle();
            card.setSubtitle(contentFragment.map(cf->cf.getElement("subtitle")).map(ContentElement::getContent).orElse(StringUtils.EMPTY));
            card.setDescription(contentFragment.map(cf->cf.getElement("description")).map(ContentElement::getContent).orElse(StringUtils.EMPTY));
            card.setImage(contentFragment.map(cf->cf.getElement("image")).map(ContentElement::getContent).orElse(StringUtils.EMPTY));
            card.setDate(contentFragment.map(cf->cf.getElement("date")).map(ContentElement::getContent).orElse(StringUtils.EMPTY));
            card.setDesignation(contentFragment.map(cf->cf.getElement("designation")).map(ContentElement::getContent).orElse(StringUtils.EMPTY));
            card.setName(contentFragment.map(cf->cf.getElement("name")).map(ContentElement::getContent).orElse(StringUtils.EMPTY));
            articles.add(card);
        }
    }

    @Override
    public List<FeaturedArticle> getArticles() {
        // TODO Auto-generated method stub
        return articles;
    }
    
}
