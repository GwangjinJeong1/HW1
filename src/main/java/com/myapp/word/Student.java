package com.myapp.word;

public class Student {
    private String stdId;
    private String name;

    public Student(String stdId, String name) {
        this.stdId = stdId;
        this.name = name;
    }

    public Student() {

    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student : " + stdId + " - " + name;
    }
}
