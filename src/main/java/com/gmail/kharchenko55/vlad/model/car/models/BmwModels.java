package com.gmail.kharchenko55.vlad.model.car.models;

public enum BmwModels {
    M1("M1",95),
    M2("M2",44856),
    M3("M3",3292),
    M4("M4",44857),
    M5("M5",3213);

    private final String name;
    private final int id;

    BmwModels(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
