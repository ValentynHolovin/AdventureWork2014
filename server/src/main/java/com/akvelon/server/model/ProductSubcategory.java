package com.akvelon.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
