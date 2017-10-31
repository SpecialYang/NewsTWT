package com.special.newsdemo.model;

/**
 * Created by Special on 2017/10/30.
 */

public class Menu {
    private String name;
    private int    type;

    public Menu(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }
}
