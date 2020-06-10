package com.gmail.kharchenko55.vlad.model.car.models;

public enum ToyotaModels {
    TUNDRA("Tundra", 2046),
    VISTA("Vista", 2397),
    RAV4("RAV4", 715),
    CAMRY("Camry", 698),
    AVALON("Avalon", 1832);

    private final String name;
    private final int id;

    ToyotaModels(String name, int id) {
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
