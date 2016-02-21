package com.dreamify;

/**
 * Created by aaronpersaud on 2016-02-20.
 */
public class Dream {
    private String title;
    private String category;
    private int ID = 0;

    public Dream(String title, String category, int ID) {
        this.title = title;
        this.category = category;
        this.ID += 1;

    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public int getID() {
        return ID;
    }
}