package com.gmail.kharchenko55.vlad.model.car;

public enum CarBody {
    SEDAN("Седан", 3),
    CROSSOVER("Внедорожник / Кроссовер", 5),
    COUPE("Купе", 6),
    CABRIOLET("Кабриолет", 7),
    HATCHBACK("Хэтчбек", 4);

    private final String name;
    private final int id;

    CarBody(String name, int id) {
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
