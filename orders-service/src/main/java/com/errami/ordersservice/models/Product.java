package com.errami.ordersservice.models;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
}