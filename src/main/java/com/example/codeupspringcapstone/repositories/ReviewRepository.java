package com.example.codeupspringcapstone.repositories;

import com.example.codeupspringcapstone.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review getPostById(long id);
    Review findById(long id);
    List <Review> findByUserId(Long id);
    List<Review> findReviewsByBrewery(String brewery);

}
