package com.test.vice20.Models;


/**
 * Created by sterlinggerritz on 8/1/16.
 */

public class ParentDrawer extends OpenDrawer {
    private String name;
    private int iconNumber;

    public ParentDrawer(String name, int iconNumber) {
        this.setName(name);
        this.setIconNumber(iconNumber);
    }

    public int getIconNumber() {
        return iconNumber;
    }

    private void setIconNumber(int iconNumber) {
        this.iconNumber = iconNumber;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}