package com.test.vice20.Drawer;

/**
 * Created by sterlinggerritz on 8/1/16.
 */
public class ContentWithoutPicture extends OpenDrawer {
    private String name;

    public ContentWithoutPicture(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
