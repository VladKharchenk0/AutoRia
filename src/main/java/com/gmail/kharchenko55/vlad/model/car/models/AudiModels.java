package com.gmail.kharchenko55.vlad.model.car.models;

public enum  AudiModels {
    A1("A1",31914),
    A2("A2",45),
    A3("A3",46),
    A4("A4",47),
    A5("A5",2032);

    private final String name;
    private final int id;

    AudiModels(String name, int id) {
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
