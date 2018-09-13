package com.ssi.mvc.domains.user.entity;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/31
 * Time:14:10
*/

public class User {
    private int age;
    private String name;
    private String id;

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
