package com.akvelon.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
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
    private String size;
    private UnitMeasure sizeUnitMeasureCode;
    private UnitMeasure weightUnitMeasureCode;
    private Double weight;
    private ProductLine productLine;
    private ProductClass productClass;
    private ProductStyle productStyle;
    private ProductSubcategory productSubcategory;
    private ProductModel productModel;
    private Date sellStartDate;
    private Date sellEndDate;
    private Date discontinuedDate;
    private List<ProductPhoto> productPhotos;
    private List<ProductReview> productReviews;
    private String rowguid;

    public Product() {
        this.rowguid = UUID.randomUUID().toString().toUpperCase();
    }
}
