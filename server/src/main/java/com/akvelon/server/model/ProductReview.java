package com.akvelon.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview extends Entity<Integer> {
    private Integer productID;
    private String reviewerName;
    private Date reviewDate;
    private String emailAddress;
    private Integer rating;
    private String comments;

}
