package com.rtr.cqrs.product.domain;

import org.mongodb.morphia.Datastore;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by dpeele on 6/7/17.
 */
public class MongoProductRepository implements ProductRepository {

    private final Datastore dataStore;

    @Inject
    public MongoProductRepository(Datastore dataStore) {
        this.dataStore = dataStore;
    }

    public Product save(Product product) {
        dataStore.save(product);
        return product;
    }

    public Optional<Product> load(int id) {
        return Optional.ofNullable(dataStore.get(Product.class,id));
    }
}
