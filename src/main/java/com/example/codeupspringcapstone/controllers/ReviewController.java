package com.example.codeupspringcapstone.controllers;

import com.example.codeupspringcapstone.models.Review;
import com.example.codeupspringcapstone.models.User;
import com.example.codeupspringcapstone.repositories.ReviewRepository;
import com.example.codeupspringcapstone.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userDAO;

    public ReviewController(ReviewRepository reviewRepository, UserRepository userDAO) {
        this.reviewRepository = reviewRepository;
        this.userDAO = userDAO;
    }



    @GetMapping("/create")
    public String setPostModel(Model model) {
        String defaultId = "default_id";
        model.addAttribute("review", new Review());
        model.addAttribute("breweryId", defaultId);

        return "create-review";
    }
//controller is not saving photos
//    @PostMapping("/create")
//    public String createPost(@ModelAttribute Review review, Model model, @RequestParam String breweryId){
//        System.out.println("Does this run?");
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User currentUser = userDAO.findByUsername(user.getUsername());
//        Review newReview = new Review();
////        String breweryId = "random-brewery-id";
//        review = reviewRepository.getPostById(1L);
//        newReview.setBrewery(breweryId);
//        newReview.setUser(currentUser);
//        newReview.setDescription(review.getDescription());
//        newReview.setImage("image.jpg");
//        newReview.setRating(10);
//        reviewRepository.save(newReview);
////        model.addAttribute("brewery", brewery);
//        model.addAttribute("review", newReview);
////        model.
////        emailService.prepareAndSend(post, post.getTitle(), post.getBody());
//        return "redirect:/view-brewery?brewery=" + breweryId;
//    }
//    -------


//controller is working saving photos and userId
    @PostMapping("/create")
public String createPost(@ModelAttribute Review review, Model model, @RequestParam String breweryId) {
    System.out.println("Does this run?");
    // Get current user from the security context
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User currentUser = userDAO.findByUsername(user.getUsername());
    // Create a new review with data from the form
    Review newReview = new Review();
    newReview.setBrewery(breweryId);
    newReview.setUser(currentUser);
    newReview.setDescription(review.getDescription());
      newReview.setImage(review.getImage());  // Assuming 'review.getImage()' gets the image URL from the form
    newReview.setRating(review.getRating());  // Set rating from the form
        newReview.setBreweryName(review.getBreweryName());  // Set brewery name from the form
    // Save the new review
    reviewRepository.save(newReview);
        model.addAttribute("review", newReview);
       return "redirect:/view-brewery?brewery=" + breweryId;
}


    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String individualPost(@PathVariable long id, Model model) {
        Review singlePost = reviewRepository.getPostById(id);
        model.addAttribute("singlePost", singlePost);
        return "view-brewery";
    }

    @GetMapping("/view-review/{id}")
    public String viewEditPostForm(@PathVariable long id, Model model) {
        Review currentReview = reviewRepository.getPostById(id);
//        Optional<Review> reviewOptional = Optional.ofNullable(reviewRepository.findById(id));
        model.addAttribute("review", currentReview);
        return "view-review";
    }


    @PostMapping("/review/edit")
    public String updatePost(@ModelAttribute Review review) {

        reviewRepository.save(review);
//        review.setDescription(description);
//        review.setRating(10);
//        reviewRepository.save(review);
        return "redirect:/profile";
    }

//    @PostMapping("/review/delete")
//    public String deletePost(@ModelAttribute Review review) {
//        System.out.println("Does this run?");
////        postDao.deleteById(id);
//        reviewRepository.delete(review);
//        return "redirect:/profile";
//    }
    @PostMapping("/review/delete")
    public String deleteProfile(@ModelAttribute Review review, @RequestParam("confirmation") String confirmation) {

        if ("confirm".equals(confirmation)) {
             reviewRepository.deleteById(review.getId());
        }   return "redirect:/profile";

    }

}
