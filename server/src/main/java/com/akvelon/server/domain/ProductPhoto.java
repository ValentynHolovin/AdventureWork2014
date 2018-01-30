package com.akvelon.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPhoto extends Entity<Integer> {
    private Blob thumbNailPhoto;
    private String thumbnailPhotoFileName;
    private Blob largePhoto;
    private String largePhotoFileName;
}