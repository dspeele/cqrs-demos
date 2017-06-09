package com.rtr.cqrs.product.command;


import com.rtr.cqrs.product.domain.Product;
import com.rtr.cqrs.product.domain.ProductRepository;
import com.rtr.cqrs.product.event.ProductCreated;

import javax.inject.Inject;

/**
 * Created by dpeele on 6/6/17.
 */
public class ProductCommandHandler {

    private final ProductRepository repository;

    @Inject
    public ProductCommandHandler(ProductRepository repository) {
        this.repository = repository;
    }


    public ProductCreated handle(CreateProduct command) throws ProductAlreadyExistsException {
        if (repository.load(command.getId()).isPresent()) {
            throw new ProductAlreadyExistsException(String.format("product already exists with id '%d'", command.getId()));
        } else {
            Product product = new Product(command.getId(), command.getCategoryId(), command.getName(), command.getDescription());
            repository.save(product);
            return new ProductCreated(command.getId(), command.getCategoryId(), command.getName(), command.getDescription());
        }
    }

}

