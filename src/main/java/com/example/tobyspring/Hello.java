package com.example.tobyspring;

public class Hello {
    private String name;
    private Integer Count;

    public Hello(String name, Integer count) {
        this.name = name;
        Count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }
}
