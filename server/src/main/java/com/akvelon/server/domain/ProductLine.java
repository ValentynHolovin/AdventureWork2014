package com.akvelon.server.domain;

import lombok.Getter;

@Getter
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
