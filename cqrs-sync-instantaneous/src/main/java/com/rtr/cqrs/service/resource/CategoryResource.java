package com.rtr.cqrs.service.resource;


import com.rtr.cqrs.category.domain.Category;
import com.rtr.cqrs.category.domain.CategoryRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Created by dpeele on 6/7/17.
 */
@Path("category")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    private final CategoryRepository categoryRepository;

    @Inject
    public CategoryResource(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GET
    @Path("{category_id}")
    public Response createCategory(@PathParam("category_id") int categoryId) {
        Optional<Category> category = categoryRepository.load(categoryId);
        if (category.isPresent()) {
            return Response.status(Response.Status.OK).entity(category).build();
        } else {
            throw new ClientErrorException(
                    Response
                            .status(Response.Status.NOT_FOUND)
                            .type(MediaType.TEXT_PLAIN)
                            .entity(String.format("category with id '%s' doesn't exist", categoryId))
                            .build()
            );
        }
    }
}


