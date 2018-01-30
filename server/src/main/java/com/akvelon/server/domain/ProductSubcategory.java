package com.akvelon.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductSubcategory extends Entity<Integer> {
    private String name;
    private ProductCategory category;
    private String rowguid;

    public ProductSubcategory() {
        this.rowguid = UUID.randomUUID().toString().toUpperCase();
    }
}
