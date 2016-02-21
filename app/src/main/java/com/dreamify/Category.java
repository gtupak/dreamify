package com.dreamify;

/**
 * Created by gabriel on 21/02/16.
 */
public enum Category {
    TRAVEL("Travel"),
    FINANCIAL("Financial"),
    ACTIVITY("Activity"),
    ACHIEVEMENT("Achievement");

    private final String category;

    Category(final String category){
        this.category = category;
    }

    @Override
    public String toString(){
        return category;
    }
}
