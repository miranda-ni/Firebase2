package com.example.taskapp.models;

public class User {
    private String name;
    private String avatar;
    private int age;


    public User(){

    }

    public User(String name, String avatar, int age) {
        this.name = name;
        this.avatar = avatar;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
