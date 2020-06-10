package com.gmail.kharchenko55.vlad.model.car.models;

public enum MazdaModels {
    CX3("CX-3", 48041),
    RX7("RX-7", 402),
    M3("3", 1692),
    M6("6", 393),
    COSMO("Cosmo", 47557);

    private final String name;
    private final int id;


    MazdaModels(String name, int id) {
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
