package com.cybrixsystems.backjsp.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Sale {
    private Long id;
    private int saleNum;
    private int amount;
    private float finalPrice;
    private User user;

    public Sale(Long id, int saleNum, int amount, float finalPrice) {
        this.id = id;
        this.saleNum = saleNum;
        this.amount = amount;
        this.finalPrice = finalPrice;
    }
}
