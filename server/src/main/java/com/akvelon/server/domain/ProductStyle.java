package com.akvelon.server.domain;

import lombok.Getter;

@Getter
public enum ProductStyle {
    W("Women's"),
    M("Men's"),
    U("Universal");

    private String styleName;

    ProductStyle(String styleName) {
        this.styleName = styleName;
    }
}
