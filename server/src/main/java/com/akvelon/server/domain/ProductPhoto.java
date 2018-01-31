package com.akvelon.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPhoto extends Entity<Integer> {
    private byte[] thumbNailPhoto;
    private String thumbnailPhotoFileName;
    private byte[] largePhoto;
    private String largePhotoFileName;
}