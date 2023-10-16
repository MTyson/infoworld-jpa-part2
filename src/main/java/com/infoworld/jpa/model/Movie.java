package com.infoworld.jpa.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MOVIE")
public class Movie {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;

    @ManyToMany(mappedBy = "movies", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<SuperHero> superHeroes = new HashSet<>();

    public Movie() {
    }

    public Movie(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Movie(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<SuperHero> getSuperHeroes() {
        return superHeroes;
    }

    public void addSuperHero(SuperHero superHero) {
        superHeroes.add(superHero);
        superHero.getMovies().add(this);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
