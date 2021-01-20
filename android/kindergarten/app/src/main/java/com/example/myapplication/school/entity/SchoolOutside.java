package com.example.myapplication.school.entity;

import java.util.List;

public class SchoolOutside {

    private String description;
    private List<String> list;

    public SchoolOutside() {
        super();
    }

    public SchoolOutside(String description, List<String> list) {
        super();
        this.description = description;
        this.list = list;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SchoolOutside [description=" + description + ", list=" + list + "]";
    }
}
