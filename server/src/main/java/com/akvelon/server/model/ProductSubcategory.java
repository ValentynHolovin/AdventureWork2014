package com.akvelon.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSubcategory extends Entity<Integer> {
    private String name;
    private ProductCategory category;

}
