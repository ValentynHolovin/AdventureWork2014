package com.akvelon.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Entity<Integer> {
    private String name;
    private String productNumber;
    private Boolean makeFlag;
    private Boolean finishedGoodsFlag;
    private String color;
    private Integer safetyStockLevel;
    private Integer reorderPoint;
    private Double standardCost;
    private Double listPrice;
    private Integer daysToManufacture;
    private ProductSize productSize;
    private ProductWeight productWeight;
    private ProductLine productLine;
    private ProductClass productClass;
    private ProductStyle productStyle;
    private ProductSubcategory productSubcategory;
    private ProductModel productModel;
    private Calendar sellStartDate;
    private Calendar sellEndDate;
    private Calendar discontinuedDate;
    private List<ProductPhoto> productPhotos;
    private List<ProductReview> productReviews;
}
