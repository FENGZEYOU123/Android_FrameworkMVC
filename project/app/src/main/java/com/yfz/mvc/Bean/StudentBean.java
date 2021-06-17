package com.yfz.mvc.Bean;

public class StudentBean {
    //学生名字
    private String name = "";
    //学生ID
    private int id = -1;
    //学生性别
    private String gender = "";
    //枚举-性别
    public enum Gender {
        男, 女
    }
    //创建学生对象的时候，必须传入3个参数，名字，ID，性别
    public StudentBean(String name, int id, Gender gender) {
        this.name = name;
        this.id = id;
        this.gender = gender == Gender.男?"男":"女";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
