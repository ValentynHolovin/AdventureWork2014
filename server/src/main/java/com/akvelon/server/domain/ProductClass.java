package com.akvelon.server.domain;

import lombok.Getter;

@Getter
public enum ProductClass {
    H("High"),
    M("Medium"),
    L("Low");

    private String className;

    ProductClass(String className) {
        this.className = className;
    }
}
