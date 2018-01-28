package com.akvelon.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDescription extends Entity<Integer> {
    private String description;
    private Culture culture;
}
