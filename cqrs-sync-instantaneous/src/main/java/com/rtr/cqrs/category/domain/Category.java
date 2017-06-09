package com.rtr.cqrs.category.domain;

import com.rtr.cqrs.product.domain.EmbeddedProduct;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.util.List;

/**
 * Created by dpeele on 6/6/17.
 */
@Entity("categories")
public class Category{

    @Id
    @Indexed
    private int id;
    private List<EmbeddedProduct> products;

    public Category() { }

    public Category(int id, List<EmbeddedProduct> products) {
        this.id = id;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<EmbeddedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<EmbeddedProduct> products) {
        this.products = products;
    }

    public void addProduct(EmbeddedProduct product) {
        this.products.add(product);
    }
}
