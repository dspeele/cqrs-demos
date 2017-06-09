package com.rtr.cqrs.category.domain;

import java.util.Optional;

/**
 * Created by dpeele on 6/7/17.
 */
public interface CategoryRepository {
    public Category save(Category category);

    public Optional<Category> load(int id);
}
