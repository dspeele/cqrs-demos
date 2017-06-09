package com.rtr.cqrs.product.event;

import com.rtr.cqrs.category.command.AddProduct;
import com.rtr.cqrs.category.command.CategoryCommandHandler;
import com.rtr.cqrs.category.event.ProductAdded;
import com.rtr.cqrs.product.domain.EmbeddedProduct;
import javax.inject.Inject;

/**
 * Created by dpeele on 6/8/17.
 */
public class ProductDenormalizer {
    private CategoryCommandHandler categoryCommandHandler;

    @Inject
    public ProductDenormalizer(CategoryCommandHandler categoryCommandHandler) {
        this.categoryCommandHandler = categoryCommandHandler;
    }

    public ProductAdded denormalize(ProductCreated event) {
        AddProduct command = new AddProduct(event.getCategoryId(), new EmbeddedProduct(event.getId(), event.getName(), event.getDescription()));
        return categoryCommandHandler.handle(command);
    }
}