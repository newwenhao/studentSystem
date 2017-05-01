package com.reborn;
import java.io.Serializable;

/**
 * 学生类
 *
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;// 学生编号
    private String name;// 学生姓名
    private String num;// 学生学号
    private int sex;// 学生性别 1为男 0为女

    public Student() {
    }

    public Student(int id, String name, String num, int sex) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student:@id:" + this.id + "\t@name:" + this.name + "\t@num:"
                + this.num + "\t@sex:" + ((this.sex == 0) ? "女" : "男");
    }
}