package com.gmail.kharchenko55.vlad.model.car.models;

public enum JaguarModels {
    EPACE("E-Pace",53223),
    XJL("XJL",43212),
    FTYPE("F-Type",41406),
    SL("SL",33669),
    XF("XF",2264);

    private final String name;
    private final int id;

    JaguarModels(String name, int id) {
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
