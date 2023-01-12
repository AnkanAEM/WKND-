package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.adobe.aem.guides.wknd.core.models.StudentCard;

import com.adobe.cq.dam.cfm.ContentElement;
//import org.apache.sling.models.factory.ModelFactory;
import com.adobe.cq.dam.cfm.ContentFragment;
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
//import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { StudentCard.class }, resourceType = {
        StudentCardImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class StudentCardImpl implements StudentCard {
    protected static final String RESOURCE_TYPE = "wknd/components/studentcard";
   



    @Self
    private SlingHttpServletRequest request;
    private Resource resource;

    @Inject
    ResourceResolver resourceResolver;
    private Optional<ContentFragment> contentFragment;

    @ValueMapValue
    private String student;

    // @ValueMapValue
    private String firstName;

    //@ValueMapValue
    private String lastName;


    //@ValueMapValue
    private String grade;

    // @ValueMapValue
    private String email;

    // @ValueMapValue
    private String studentImage;

   





    @PostConstruct
    private void init()
    {
        Resource fragmentResource = resourceResolver.getResource(student);
        contentFragment = Optional.ofNullable(fragmentResource.adaptTo(ContentFragment.class));
          firstName=contentFragment.map(cf -> cf.getElement("firstName")).map(ContentElement::getContent)
         .orElse(StringUtils.EMPTY);

        lastName=contentFragment.map(cf -> cf.getElement("lastName")).map(ContentElement::getContent)
         .orElse(StringUtils.EMPTY);

        grade=contentFragment.map(cf -> cf.getElement("grade")).map(ContentElement::getContent)
        .orElse(StringUtils.EMPTY);

         email=contentFragment.map(cf -> cf.getElement("email")).map(ContentElement::getContent)
         .orElse(StringUtils.EMPTY);

        studentImage=contentFragment.map(cf -> cf.getElement("studentImage")).map(ContentElement::getContent)
         .orElse(StringUtils.EMPTY);


        


    }


    @Override
    public String getFirstName() 
    {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }


    @Override
    public String getEmail() {
        return email;
    }


    @Override
    public String getStudentImage() {
        return studentImage;
    }

    @Override
    public String getGrade() {
        return grade;
    }
    




  
    

    
}
   