package com.example.task71.model;

public class Model {
    String title;
    String phone;
    String description;
    String date;
    String location;
    String id;

    public Model(String id, String title, String phone, String description, String date, String location) {
        this.title = title;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}