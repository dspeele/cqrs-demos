package com.rtr.cqrs.product.domain;

import java.util.Optional;

/**
 * Created by dpeele on 6/7/17.
 */
public interface ProductRepository {
    public Product save(Product product);

    public Optional<Product> load (int id);
}
