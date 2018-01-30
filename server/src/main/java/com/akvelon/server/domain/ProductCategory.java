package com.akvelon.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductCategory extends Entity<Integer> {
    private String name;
    private String rowguid;

    public ProductCategory() {
        this.rowguid = UUID.randomUUID().toString().toUpperCase();
    }
}
