package com.akvelon.server.domain;

import lombok.Data;

@Data
public class Entity<T> {
    private T id;
}
