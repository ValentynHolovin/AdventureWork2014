package com.akvelon.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductWeight {
    private Double weight;
    private String weightUnitMeasureCode;
}
