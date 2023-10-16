package com.infoworld.jpa;

import com.infoworld.jpa.model.Movie;
import com.infoworld.jpa.model.SuperHero;
import com.infoworld.jpa.repository.MovieRepository;
import com.infoworld.jpa.repository.SuperHeroRepository;
import org.hibernate.Session;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaExample2 {

    public static void main(String[] args) {
        // Create our entity manager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuperHeroes");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        MovieRepository movieRepository = new MovieRepository(entityManager);
        SuperHeroRepository superHeroRepository = new SuperHeroRepository(entityManager);

        // Create some super heroes
        SuperHero ironman = new SuperHero("Iron Man");
        SuperHero thor = new SuperHero("Thor");

        // Create some movies
        Movie avengers = new Movie("The Avengers");
        avengers.addSuperHero(ironman);
        avengers.addSuperHero(thor);

        Movie infinityWar = new Movie("Avengers: Infinity War");
        infinityWar.addSuperHero(ironman);
        infinityWar.addSuperHero(thor);

        // Save the movies
        movieRepository.save(avengers);
        movieRepository.save(infinityWar);

        // Find all movies
        System.out.println("MOVIES:");
        movieRepository.findAll().forEach(movie -> {
            System.out.println("Movie: [" + movie.getId() + "] - " + movie.getTitle());
            movie.getSuperHeroes().forEach(System.out::println);
        });

        // Find all superheros
        System.out.println("\nSUPER HEROES:");
        superHeroRepository.findAll().forEach(superHero -> {
            System.out.println(superHero);
            superHero.getMovies().forEach(System.out::println);
        });

        // Delete a movie and verify that its superheroes are not deleted
        movieRepository.deleteById(1);
        System.out.println("\nMOVIES (AFTER DELETE):");
        movieRepository.findAll().forEach(movie -> {
            System.out.println("Movie: [" + movie.getId() + "] - " + movie.getTitle());
            movie.getSuperHeroes().forEach(System.out::println);
        });
        System.out.println("\nSUPER HEROES (AFTER DELETE):");
        superHeroRepository.findAll().forEach(superHero -> {
            System.out.println(superHero);
            superHero.getMovies().forEach(System.out::println);
        });


        // DEBUG, dump our tables
        entityManager.unwrap(Session.class).doWork(connection -> {
            JdbcUtils.dumpTables(connection, "MOVIE", "SUPER_HERO", "SUPERHERO_MOVIES");
            JdbcUtils.dumpTableNames(connection);
        });

        // Close the entity manager and associated factory
        entityManager.close();
        entityManagerFactory.close();
    }
}
