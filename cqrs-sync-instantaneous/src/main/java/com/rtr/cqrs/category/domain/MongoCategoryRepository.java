package com.rtr.cqrs.category.domain;

import org.mongodb.morphia.Datastore;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by dpeele on 6/7/17.
 */
public class MongoCategoryRepository implements CategoryRepository {

    private final Datastore dataStore;

    @Inject
    public MongoCategoryRepository(Datastore dataStore) {
        this.dataStore = dataStore;
    }

    public Category save(Category product) {
        dataStore.save(product);
        return product;
    }

    public Optional<Category> load(int id) {
        return Optional.ofNullable(dataStore.get(Category.class,id));
    }
}
