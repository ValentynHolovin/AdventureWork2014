package com.akvelon.server.model;

import lombok.Data;

@Data
public class Entity<T> {
    private T id;
}
