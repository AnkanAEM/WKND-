package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.adobe.aem.guides.wknd.core.models.UserDetail;

@Model(adaptables = SlingHttpServletRequest.class ,adapters = { UserDetail.class },resourceType = {
    UserDetailImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class UserDetailImpl implements UserDetail {
    protected static final String RESOURCE_TYPE = "wknd/components/multifieldtesting";

    @Inject
    String title;

    @SlingObject
    Resource componentResource;

    private List<Map<String, Object>> usersList = new ArrayList<>();
    
    @PostConstruct
    protected void init(){
        Resource usersResource = componentResource.getChild("users");
        if(usersList!=null){
            for(Resource user : usersResource.getChildren()){
                Map<String, Object> usersMap = new HashMap<>();
                usersMap.put("name", user.getValueMap().get("name", String.class));
                usersMap.put("age", user.getValueMap().get("age", String.class));

                Resource hobbyResource = user.getChild("hobbies");
                if(hobbyResource!= null){
                    List<Map<String,String>> hobbyList = new ArrayList<>();
                    for(Resource hobby: hobbyResource.getChildren()){
                        Map<String,String> hobbyMap = new HashMap<>();
                        hobbyMap.put("like",hobby.getValueMap().get("like",String.class));
                        hobbyMap.put("reason",hobby.getValueMap().get("reason",String.class));
                        hobbyList.add(hobbyMap);
                    }
                    usersMap.put("hobbies",hobbyList);
                }
            usersList.add(usersMap); 
            }
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<Map<String, Object>> getUsers() {
        return usersList;
    }
    
}
