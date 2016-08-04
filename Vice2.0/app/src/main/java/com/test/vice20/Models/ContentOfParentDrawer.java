package com.test.vice20.Models;

public class ContentOfParentDrawer extends OpenDrawer {
    private String name;

    public ContentOfParentDrawer(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
