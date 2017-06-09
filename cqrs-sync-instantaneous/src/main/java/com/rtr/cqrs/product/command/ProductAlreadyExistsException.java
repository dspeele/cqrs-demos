package com.rtr.cqrs.product.command;

/**
 * Created by dpeele on 6/7/17.
 */
public class ProductAlreadyExistsException extends Exception{
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}