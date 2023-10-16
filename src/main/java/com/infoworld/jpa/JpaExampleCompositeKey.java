package com.infoworld.jpa;

import com.infoworld.jpa.model.ProductPrice;
import com.infoworld.jpa.model.ProductPriceId;
import org.hibernate.Session;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class JpaExampleCompositeKey {
    public static void main(String[] args) {
        // Create our entity manager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuperHeroes");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Create a product price
        ProductPrice productPrice1 = new ProductPrice(new ProductPriceId("EAST", 1), 500.0d);
        ProductPrice productPrice2 = new ProductPrice(new ProductPriceId("WEST", 1), 400.0d);
        ProductPrice productPrice3 = new ProductPrice(new ProductPriceId("EAST", 2), 200.0d);
        ProductPrice productPrice4 = new ProductPrice(new ProductPriceId("WEST", 2), 150.0d);

        try {
            // Save the product prices to the database
            entityManager.getTransaction().begin();
            entityManager.persist(productPrice1);
            entityManager.persist(productPrice2);
            entityManager.persist(productPrice3);
            entityManager.persist(productPrice4);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Query for product1 by its ID
        ProductPrice productPrice = entityManager.find(ProductPrice.class, new ProductPriceId("EAST", 1));
        System.out.println(productPrice);

        // Find all product prices
        List<ProductPrice> productPrices = entityManager.createQuery("from ProductPrice").getResultList();
        System.out.println("\nAll Product Prices:");
        productPrices.forEach(System.out::println);

        // DEBUG, dump our tables
        entityManager.unwrap(Session.class).doWork(connection ->
                JdbcUtils.dumpTables(connection, "PRODUCT_PRICE"));

        // Close the entity manager and associated factory
        entityManager.close();
        entityManagerFactory.close();
    }
}
