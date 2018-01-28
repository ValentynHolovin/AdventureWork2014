package com.akvelon.server.model;

public enum ProductLine {
    R("Road"),
    M("Mountain"),
    T("Touring"),
    S("Standard");

    private String lineName;

    ProductLine(String lineName) {
        this.lineName = lineName;
    }
}
