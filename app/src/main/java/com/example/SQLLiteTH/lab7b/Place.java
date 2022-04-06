package com.example.SQLLiteTH.lab7b;

public class Place {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Place(String name) {
        this.name = name;
    }

    public Place() {
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                '}';
    }
}
