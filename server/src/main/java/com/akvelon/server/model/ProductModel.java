package com.akvelon.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel extends Entity<Integer> {
    private String name;
    private String catalogDescription;
    private String instruction;
    private List<Illustration> illustrations;
    private List<ProductDescription> productDescriptions;
}
