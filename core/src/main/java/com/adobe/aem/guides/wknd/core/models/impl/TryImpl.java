package com.adobe.aem.guides.wknd.core.models.impl;
import com.adobe.aem.guides.wknd.core.models.Try;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

// import java.security.ProtectionDomain;

import javax.annotation.PostConstruct;

// import org.apache.sling.api.resource.ResourceResolver;

// import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model
        (adaptables = { SlingHttpServletRequest.class }, 
        adapters = { Try.class }, 
        resourceType = {TryImpl.RESOURCE_TYPE}, 
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class TryImpl implements Try {
    protected static final String RESOURCE_TYPE = "wknd/components/try";

    @ValueMapValue
    private String name;

    @ValueMapValue
    private String occupations;

    @ValueMapValue
    private List<String> headlines;

    @PostConstruct
        private void init(){
            name = "Name is ok "+getName()+"<br>"+"And occupation is "+getOccupation();
        }

    @Override
    public String getOccupation() {
        return(occupations);
    }

    @Override
    public String getName() {
       return(name);
    }

    @Override
    public List<String> getHeadlines() {
        if(headlines != null){
            return new ArrayList<String>(headlines);
        }
        else{
            return Collections.emptyList();
        }
    }
}
