package com.rtr.cqrs.category.event;

import com.rtr.cqrs.product.domain.EmbeddedProduct;

/**
 * Created by dpeele on 6/6/17.
 */
public class ProductAdded {

    private final int id;
    private final EmbeddedProduct product;

    public ProductAdded(int id, EmbeddedProduct product) {
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
