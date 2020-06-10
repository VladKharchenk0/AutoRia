package com.gmail.kharchenko55.vlad.model.car.models;

public enum MercedesModels {
    MAYBACH("Maybach S 400", 59469),
    C63("C 63 AMG", 44004),
    E430("E 430", 41868),
    A190("A 190", 40702),
    GLE63("GLE 63", 60710);

    private final String name;
    private final int id;


    MercedesModels(String name, int id) {
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
