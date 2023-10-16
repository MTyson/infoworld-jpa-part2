package com.infoworld.jpa;

import com.infoworld.jpa.model.Car;
import com.infoworld.jpa.model.SportsCar;
import com.infoworld.jpa.model.SportsUtilityVehicle;
import org.hibernate.Session;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class JpaExampleInheritance {
    public static void main(String[] args) {
        // Create our entity manager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuperHeroes");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        SportsCar sportsCar = new SportsCar("Porche", "Carrera", 2018, 150, 4.5);
        SportsUtilityVehicle suv = new SportsUtilityVehicle("Mazda", "CX-9", 2017, true, 3000);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(sportsCar);
            entityManager.persist(suv);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Car> cars = entityManager.createQuery("from Car").getResultList();
        System.out.println("CARS:");
        cars.forEach(System.out::println);

        // DEBUG, dump our tables
        entityManager.unwrap(Session.class).doWork(connection -> {
            JdbcUtils.dumpTableNames(connection);
//            JdbcUtils.dumpTables(connection, "CAR");
            JdbcUtils.dumpTables(connection, "CAR", "SPORTSCAR", "SUV");
        });

        // Close the entity manager and associated factory
        entityManager.close();
        entityManagerFactory.close();

    }
}
