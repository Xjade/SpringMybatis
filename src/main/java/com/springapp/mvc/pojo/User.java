package com.springapp.mvc.pojo;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/15
 * Time:17:29
*/

public final class User {
    private int age;
    private String name;
    private String id;

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

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
