package com.example.customerlist;

public class CustomerModel {
    private  int id;
    private String name;
    private  int age;
    private  String imageUrl = "";

    public CustomerModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public CustomerModel(String name, int age, String imageUrl) {
        this.name = name;
        this.age = age;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
