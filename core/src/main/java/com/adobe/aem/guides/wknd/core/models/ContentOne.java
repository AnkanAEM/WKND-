package com.adobe.aem.guides.wknd.core.models;

public class ContentOne {
    String title;
    String cardImage;
    String description;
    String date;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getCardImage(){
        return cardImage;
    }

    public void setCardImage(String cardImage){
        this.cardImage=cardImage;
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
}
