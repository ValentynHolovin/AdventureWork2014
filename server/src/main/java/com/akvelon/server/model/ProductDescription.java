package com.akvelon.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductDescription extends Entity<Integer> {
    private String description;
    private Culture culture;
    private String rowguid;

    public ProductDescription() {
        this.rowguid = UUID.randomUUID().toString().toUpperCase();
    }
}
