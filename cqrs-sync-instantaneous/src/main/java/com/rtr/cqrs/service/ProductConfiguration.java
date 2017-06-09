package com.rtr.cqrs.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meltmedia.dropwizard.mongo.MongoConfiguration;
import io.dropwizard.Configuration;

/**
 * Created by dpeele on 6/6/17.
 */
public class ProductConfiguration extends Configuration {

    @JsonProperty
    protected MongoConfiguration product_mongo;

    @JsonProperty
    protected MongoConfiguration category_mongo;

    public MongoConfiguration getProductMongo() {
        return product_mongo;
    }

    public MongoConfiguration getCategoryMongo() {
        return category_mongo;
    }

}
