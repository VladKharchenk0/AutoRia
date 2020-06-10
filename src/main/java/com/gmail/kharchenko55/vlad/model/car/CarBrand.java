package com.gmail.kharchenko55.vlad.model.car;

public enum CarBrand {
    AUDI("Audi",  6),
    BMW("BMW", 9),
    MERCEDES("Mercedes-Benz", 48),
    JAGUAR("Jaguar",31),
    TOYOTA("Toyota",79),
    MAZDA("Mazda",47);

    private final String name;
    private final int id;

    CarBrand(String name, int id) {
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
