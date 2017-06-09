package com.rtr.cqrs.service.resource;

import com.rtr.cqrs.product.event.ProductDenormalizer;
import com.rtr.cqrs.service.api.CreateProductRequest;
import com.rtr.cqrs.product.command.CreateProduct;
import com.rtr.cqrs.product.command.ProductAlreadyExistsException;
import com.rtr.cqrs.product.command.ProductCommandHandler;
import com.rtr.cqrs.product.event.ProductCreated;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dpeele on 6/7/17.
 */
@Path("product")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private ProductCommandHandler productCommandHandler;
    private ProductDenormalizer productDenormalizer;

    @Inject
    public ProductResource(ProductCommandHandler productCommandHandler, ProductDenormalizer productDenormalizer) {
        this.productCommandHandler = productCommandHandler;
        this.productDenormalizer = productDenormalizer;
    }


    @POST
    @Path("{product_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@PathParam("product_id") int productId, CreateProductRequest request) {
        CreateProduct createProduct = new CreateProduct(productId, request.getCategoryId(), request.getName(), request.getDescription());
        try {
            ProductCreated productCreated = productCommandHandler.handle(createProduct);
            productDenormalizer.denormalize(productCreated);
            return Response.status(Response.Status.CREATED).entity(productCreated).build();
        }
        catch (ProductAlreadyExistsException ex) {
            throw new ClientErrorException(
                    Response
                            .status(Response.Status.CONFLICT)
                            .type(MediaType.TEXT_PLAIN)
                            .entity(ex.getMessage())
                            .build()
            );
        }


    }
}


