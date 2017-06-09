package com.rtr.cqrs.service.api;


/**
 * Created by dpeele on 6/6/17.
 */
public class CreateProductRequest {

    private int categoryId;
    private String name;
    private String description;

    public CreateProductRequest(int categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    public CreateProductRequest() { }

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
