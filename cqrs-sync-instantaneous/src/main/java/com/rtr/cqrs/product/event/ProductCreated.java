package com.rtr.cqrs.product.event;

/**
 * Created by dpeele on 6/6/17.
 */
public class ProductCreated {

    private final int id;
    private final int categoryId;
    private final String name;
    private final String description;

    public ProductCreated(int id, int categoryId, String name, String description) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
