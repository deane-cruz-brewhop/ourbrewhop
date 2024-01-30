package com.example.codeupspringcapstone.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rating")
    private int rating;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "review")
    private List<Likes> likes;

    @Column(name = "brewery_id", nullable = true)
    private String brewery;

    @Column(name = "brewery_name", nullable = true)
    private String breweryName; // New field for brewery name

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors

    public Review() {}
    public Review(int rating, String image, String description) {
        this.rating = rating;
        this.image = image;
        this.description = description;
    }
    public Review(User user, int rating, String image, String description) {
        this.user = user;
        this.rating = rating;
        this.image = image;
        this.description = description;
    }

    public Review(long id, int rating, String image, String description) {
        this.rating = rating;
        this.id = id;
        this.image = image;
        this.description = description;
    }
    public Review(long id, int rating, String image, String description, List<Likes> likes) {
        this.id = id;
        this.rating = rating;
        this.image = image;
        this.description = description;
        this.likes = likes;
    }
    public Review(long id, int rating, String image, String description, List<Likes> likes, String brewery) {
        this.id = id;
        this.rating = rating;
        this.image = image;
        this.description = description;
        this.likes = likes;
        this.brewery = brewery;
    }
    public Review(long id, int rating, String image, String description, List<Likes> likes, String brewery, String breweryName) {
        this.id = id;
        this.rating = rating;
        this.image = image;
        this.description = description;
        this.likes = likes;
        this.brewery = brewery;
        this.breweryName = breweryName; // Set brewery name
    }
    public Review(long id, int rating, String image, String description, List<Likes> likes, String brewery, String breweryName, User user) {
        this.id = id;
        this.rating = rating;
        this.image = image;
        this.description = description;
        this.likes = likes;
        this.brewery = brewery;
        this.breweryName = breweryName; // Set brewery name
        this.user = user;
    }

    // Getters and setters
    public Review(String description) {
        this.description = description;
    }
    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }
    public void setRating (int rating) {
        this.rating = rating;
    }
    public int getRating () {
        return rating;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId () {
        return id;
    }
    public void setImage (String image) {
        this.image = image;
    }
    public String getImage () {
        return image;
    }
    public void setDescription (String description) {
        this.description = description;
    }
    public String getDescription () {
        return description;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
