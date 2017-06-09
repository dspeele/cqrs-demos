package com.rtr.cqrs.product.domain;

import org.mongodb.morphia.annotations.*;

/**
 * Created by dpeele on 6/6/17.
 */
@Entity("products")
public class Product {

    @Id
    @Indexed
    private int id;
    private int categoryId;
    private String name;
    private String description;

    public Product() { }

    public Product(int id, int categoryId, String name, String description) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
