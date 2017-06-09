package com.rtr.cqrs.product.domain;


import org.mongodb.morphia.annotations.Embedded;

/**
 * Created by dpeele on 6/6/17.
 */
@Embedded
public class EmbeddedProduct {

    private int id;
    private String name;
    private String description;

    public EmbeddedProduct() { }

    public EmbeddedProduct(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
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
