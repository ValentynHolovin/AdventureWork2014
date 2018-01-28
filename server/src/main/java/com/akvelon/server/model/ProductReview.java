package com.akvelon.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview extends Entity<Integer> {
    private String reviewerName;
    private Calendar reviewDate;
    private String emailAddress;
    private Integer rating;
    private String comments;

}
