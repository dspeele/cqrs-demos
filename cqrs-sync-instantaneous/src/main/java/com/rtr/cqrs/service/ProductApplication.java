package com.rtr.cqrs.service;

import com.meltmedia.dropwizard.mongo.MongoBundle;
import com.rtr.cqrs.service.injection.ProductBinder;
import com.rtr.cqrs.service.resource.CategoryResource;
import com.rtr.cqrs.service.resource.ProductResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by dpeele on 6/6/17.
 */
public class ProductApplication extends Application<ProductConfiguration> {

    private MongoBundle<ProductConfiguration> productMongoBundle;
    private MongoBundle<ProductConfiguration> categoryMongoBundle;

    @Override
    public void initialize(Bootstrap<ProductConfiguration> bootstrap) {
        bootstrap.addBundle(productMongoBundle = MongoBundle.<ProductConfiguration>builder()
                .withConfiguration(ProductConfiguration::getProductMongo)
                .build());
        bootstrap.addBundle(categoryMongoBundle = MongoBundle.<ProductConfiguration>builder()
                .withConfiguration(ProductConfiguration::getCategoryMongo)
                .build());
    }

    @Override
    public void run(ProductConfiguration configuration,
                    Environment environment) {
        environment.jersey().register(new ProductBinder(configuration, environment, productMongoBundle, categoryMongoBundle));
        environment.jersey().register(ProductResource.class);
        environment.jersey().register(CategoryResource.class);
    }

    public static void main(String[] args) throws Exception {
        new ProductApplication().run(args);
    }
}
