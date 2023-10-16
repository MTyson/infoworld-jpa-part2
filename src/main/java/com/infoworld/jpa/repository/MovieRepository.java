package com.infoworld.jpa.repository;

import com.infoworld.jpa.model.Movie;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * Implements persistence methods for movies.
 */
public class MovieRepository {
    private EntityManager entityManager;

    public MovieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Saves the specified movie to the database.
     *
     * @param movie The movie to save to the database.
     * @return The saved movie, wrapped in an optional, or Optional.empty() if the save operation fails.
     */
    public Optional<Movie> save(Movie movie) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(movie);
            entityManager.getTransaction().commit();
            return Optional.of(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Finds the movie with the specified ID.
     *
     * @param id The ID of the movie to find.
     * @return The requested movie, wrapped in an optional, if it exists, otherwise Optional.empty().
     */
    public Optional<Movie> findById(Integer id) {
        Movie movie = entityManager.find(Movie.class, id);
        return movie != null ? Optional.of(movie) : Optional.empty();
    }

    /**
     * Returns a list of all movies in the database.
     *
     * @return A list of all movies in the database.
     */
    public List<Movie> findAll() {
        return entityManager.createQuery("from Movie").getResultList();
    }

    /**
     * Deletes the movie with the specified ID. This method deletes the movie and all references to its Super Heroes,
     * but does not delete the Super Heroes themselves.
     *
     * @param id The ID of the movie to delete.
     */
    public void deleteById(Integer id) {
        // Retrieve the movie with this ID
        Movie movie = entityManager.find(Movie.class, id);
        if (movie != null) {
            try {
                // Start a transaction because we're going to change the database
                entityManager.getTransaction().begin();

                // Remove all references to this movie by super heroes
                movie.getSuperHeroes().forEach(superHero -> {
                    superHero.getMovies().remove(movie);
                });

                // Now remove the movie
                entityManager.remove(movie);

                // Commit the transaction
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
