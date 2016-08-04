package com.test.vice20.Models;

public class CheckBox extends OpenDrawer{
    private String name;
    private boolean checked;

    public CheckBox(String name) {
        this.setName(name);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

}
