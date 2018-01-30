package com.akvelon.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductModel extends Entity<Integer> {
    private String name;
    private String catalogDescription;
    private String instruction;
    private List<Illustration> illustrations;
    private List<ProductDescription> productDescriptions;
    private String rowguid;

    public ProductModel() {
        this.rowguid = UUID.randomUUID().toString().toUpperCase();
    }
}
