package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import com.adobe.aem.guides.wknd.core.models.carousel;
import com.adobe.aem.guides.wknd.core.models.heroCarousel;
import com.adobe.aem.guides.wknd.core.models.string;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { heroCarousel.class }, resourceType = {
    heroCarouselImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)



public class heroCarouselImpl implements heroCarousel {

    protected static final String RESOURCE_TYPE = "wknd/components/herocarousel";
    
    @SlingObject
    private Resource resource;

    @Self
    private SlingHttpServletRequest request;
    
    @ValueMapValue
    private List<carousel> carousel;

    @PostConstruct
    public void init(){
        carousel = new ArrayList<>();
        Resource res = resource.getChild("carousel");
        if (res!=null && res.hasChildren()){
            Iterator<Resource> cards = res.listChildren();

            
            while(cards.hasNext()){
            Resource card = cards.next();
            carousel tempCarousel = new carousel();
            tempCarousel.setDescription(card.getValueMap().get("description",String.class));
            tempCarousel.setImage(card.getValueMap().get("image",String.class));
            carousel.add(tempCarousel);


                
            }
        }
    }


    @Override
    public List<carousel> getCarousel() {
        return carousel;
    }
    
}
