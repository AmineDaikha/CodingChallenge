package com.example.challengeapplication.Models;

import android.widget.LinearLayout;

public class Category {

    private int id;
    private String name;
    private boolean selected;
    private LinearLayout selectedLayout;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public LinearLayout getSelectedLayout() {
        return selectedLayout;
    }

    public void setSelectedLayout(LinearLayout selectedLayout) {
        this.selectedLayout = selectedLayout;
    }
}
