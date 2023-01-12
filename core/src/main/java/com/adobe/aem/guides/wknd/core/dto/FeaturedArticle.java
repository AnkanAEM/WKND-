package com.adobe.aem.guides.wknd.core.dto;

public class FeaturedArticle {
    String subtitle;
    String image;
    String description;
    String date;
    String designation;
    String name;

    public String getSubtitle(){
        return subtitle;
    }

    public void setSubtitle(String subtitle){
        this.subtitle=subtitle;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image=image;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date=date;
    }

    public String getDesignation(){
        return designation;
    }

    public void setDesignation(String designation){
        this.designation=designation;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }
}
