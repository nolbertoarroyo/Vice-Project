package com.test.vice20.Models;

public class Toggle extends OpenDrawer{
    private String name;
    private boolean selected;

    public Toggle(String name) {
        this.setName(name);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

}
