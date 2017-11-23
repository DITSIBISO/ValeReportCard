package com.example.android.valereportcard;

/**
 * Created by eKasiLab Alex CDTB on 2017/11/23.
 */

public class StudentActivityClass {
    private int roll_no;
    private String name;
    private int marks;

    public int getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public StudentActivityClass(int roll_no, String name, int marks) {
        this.roll_no = roll_no;
        this.name = name;
        this.marks = marks;

    }
}
