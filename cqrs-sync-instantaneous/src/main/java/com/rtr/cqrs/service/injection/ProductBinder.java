package com.rtr.cqrs.service.injection;


import com.meltmedia.dropwizard.mongo.MongoBundle;
import com.rtr.cqrs.category.command.CategoryCommandHandler;
import com.rtr.cqrs.category.domain.Category;
import com.rtr.cqrs.category.domain.CategoryRepository;
import com.rtr.cqrs.category.domain.MongoCategoryRepository;
import com.rtr.cqrs.product.command.ProductCommandHandler;
import com.rtr.cqrs.product.domain.EmbeddedProduct;
import com.rtr.cqrs.product.domain.MongoProductRepository;
import com.rtr.cqrs.product.domain.Product;
import com.rtr.cqrs.product.event.ProductDenormalizer;
import io.dropwizard.setup.Environment;
import com.rtr.cqrs.service.ProductConfiguration;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * Created by dpeele on 6/8/17.
 */
public class ProductBinder extends AbstractBinder{
    private final ProductConfiguration configuration;
    private final Environment environment;
    private final MongoBundle<ProductConfiguration> productMongoBundle;
    private final MongoBundle<ProductConfiguration> categoryMongoBundle;

    public ProductBinder(ProductConfiguration configuration, Environment environment, MongoBundle<ProductConfiguration> productMongoBundle, MongoBundle<ProductConfiguration> categoryMongoBundle) {
        this.configuration = configuration;
        this.environment = environment;
        this.productMongoBundle = productMongoBundle;
        this.categoryMongoBundle = categoryMongoBundle;
    }

    @Override
    protected void configure() {
        final Morphia morphia = new Morphia();
        morphia.map(Product.class);
        morphia.map(EmbeddedProduct.class);
        morphia.map(Category.class);
        final Datastore productDataStore = morphia.createDatastore(productMongoBundle.getClient(), productMongoBundle.getConfiguration().getDatabase());
        productDataStore.ensureIndexes();
        final Datastore categoryDataStore = morphia.createDatastore(categoryMongoBundle.getClient(), categoryMongoBundle.getConfiguration().getDatabase());
        categoryDataStore.ensureIndexes();
        MongoProductRepository mongoProductRepository = new MongoProductRepository(productDataStore);
        MongoCategoryRepository mongoCategoryRepository = new MongoCategoryRepository(categoryDataStore);
        ProductCommandHandler productCommandHandler = new ProductCommandHandler(mongoProductRepository);
        CategoryCommandHandler categoryCommandHandler = new CategoryCommandHandler(mongoCategoryRepository);
        ProductDenormalizer productDenormalizer = new ProductDenormalizer(categoryCommandHandler);
        bind(productCommandHandler).to(ProductCommandHandler.class);
        bind(productDenormalizer).to(ProductDenormalizer.class);
        bind(mongoCategoryRepository).to(CategoryRepository.class);
    }

}
