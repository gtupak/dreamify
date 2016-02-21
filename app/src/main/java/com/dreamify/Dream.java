package com.dreamify;

import java.io.Serializable;

/**
 * Created by aaronpersaud on 2016-02-20.
 */
public class Dream implements Serializable{
    private String title;
    private String details;
    private Category[] category;
    private boolean isChecked;
    private int ID;



    public Dream(String title, String details, Category[] category, int ID) {
        this.title = title;
        this.details = details;
        this.category = category;
        this.ID = ID;
        this.isChecked = false;
    }

    public String getDetails() {
        return details;
    }

    public Category[] getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public int getID() {
        return ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setCategory(Category[] category) {
        this.category = category;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}