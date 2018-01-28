package com.akvelon.server.model;

public enum ProductClass {
    H("High"),
    M("Medium"),
    L("Low");

    private String className;

    ProductClass(String className) {
        this.className = className;
    }
}
