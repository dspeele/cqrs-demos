package com.rtr.cqrs.category.command;


import com.rtr.cqrs.product.domain.EmbeddedProduct;

/**
 * Created by dpeele on 6/6/17.
 */
public class AddProduct {

    private final int id;
    private final EmbeddedProduct product;

    public AddProduct(int id, EmbeddedProduct product) {
        this.id = id;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public EmbeddedProduct getProduct() {
        return product;
    }
}
