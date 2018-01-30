package com.akvelon.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Illustration extends Entity<Integer> {
    private String diagram;
}
