package com.rtr.cqrs.category.command;


import com.google.common.collect.ImmutableSet;
import com.rtr.cqrs.category.domain.Category;
import com.rtr.cqrs.category.domain.CategoryRepository;
import com.rtr.cqrs.category.event.ProductAdded;
import com.rtr.cqrs.product.domain.EmbeddedProduct;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by dpeele on 6/6/17.
 */
public class CategoryCommandHandler {

    private final CategoryRepository repository;

    @Inject
    public CategoryCommandHandler(CategoryRepository repository) {
        this.repository = repository;
    }


    public ProductAdded handle(AddProduct command) {
        Category category = repository.load(command.getId())
                .orElse(new Category(command.getId(), new ArrayList<>()));
        category.addProduct(command.getProduct());
        repository.save(category);
        return new ProductAdded(command.getId(), command.getProduct());
    }

}

