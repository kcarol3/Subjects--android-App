package com.example.firstapplication;

public class MarksModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public MarksModel(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }

    private String name;
    private int mark;
}
