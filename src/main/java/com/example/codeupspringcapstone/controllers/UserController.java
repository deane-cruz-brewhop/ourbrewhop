package com.example.codeupspringcapstone.controllers;

import com.example.codeupspringcapstone.models.AvatarImage;
import com.example.codeupspringcapstone.models.Review;
import com.example.codeupspringcapstone.models.User;
import com.example.codeupspringcapstone.repositories.ReviewRepository;
import com.example.codeupspringcapstone.repositories.UserRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

// import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private ReviewRepository reviewRepository;
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(ReviewRepository reviewRepository, UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.reviewRepository = reviewRepository;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getAllUsers() {
        return "index";
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        // Check if username already exists
        if (userDao.existsByUsername(user.getUsername())) {
            model.addAttribute("usernameError", "Username unavailable, please enter a new username");
            // Clear the username field
            user.setUsername("");
            model.addAttribute("user", user);
            return "users/sign-up";
        }
        // Check if email already exists
        if (userDao.existsByEmail(user.getEmail())) {
            model.addAttribute("emailError", "Email already in use, please try a different one");
            // Clear the email field
            user.setEmail("");
            model.addAttribute("user", user);
            return "users/sign-up";
        }
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("user", user);
            return "users/sign-up";
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userDao.save(user);
        return "redirect:/sign-in";
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUsersById(loggedInUser.getId());
        model.addAttribute("user", user);
        Long userId = loggedInUser.getId();
        List<Review> userReview = reviewRepository.findByUserId(userId);

        model.addAttribute("userReviews", userReview);
        return "users/profile";
    }
//^^^^^^^^^^^^^^^^^^^^^^^these work without an image
    @GetMapping("/profile/edit/{id}")
    public String editProfile(@PathVariable Long id, Model model) {
        User user = userDao.getUsersById(id);
        model.addAttribute("user", user);
        return "users/edit-profile"; // Corrected the return path
    }

    //this one works
    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute User user) {
        System.out.println(user.getPassword());
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userDao.save(user);
        return "redirect:/profile";
    }
//
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


//    /^--^--^--^--^--^--^--^--^--^--^--^--^--^--^--^--^-
    //    not working yet trying to be able to edit profile with image
//@GetMapping("/profile/edit/{id}")
//public String editProfile(@PathVariable Long id, Model model) {
//    User user = userDao.getUsersById(id);
//    AvatarImage avatarImage = // Retrieve the AvatarImage entity for the user
//
//            String avatarImageUrl = (avatarImage != null) ? avatarImage.getImage() : null;
//
//    model.addAttribute("user", user);
//    model.addAttribute("avatarImageUrl", avatarImageUrl);
//    return "users/edit-profile";
//}

//    not working yet trying to be able to edit profile with image
//    @PostMapping("/profile/edit")
//    public String updateProfile(@ModelAttribute User user, @RequestParam(required = false) String profileImageUrl) {
//        // Update user details
//        String hashedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hashedPassword);
//        userDao.save(user);
//
//        // Update AvatarImage entity
//        if (profileImageUrl != null && !profileImageUrl.isEmpty()) {
//            AvatarImage avatarImage = // Retrieve or create a new AvatarImage entity for the user
//                    avatarImage.setImage(profileImageUrl);
//            avatarImage.setUser(user);
//            // Save the AvatarImage entity
//        }
//
//        return "redirect:/profile";
//    }
//-^--^--^--^--^--^--^--^--^--^--^--^--^--^--^--^--^-
    @PostMapping("/delete-profile")
    public String deleteProfile() {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDao.deleteById(loggedInUser.getId());
        return "redirect:/sign-in";
    }
    @GetMapping("/all-reviews")
    public String displayAllReviews(Model model) {
        List<Review> allReviews = reviewRepository.findAll(); // Change to findAll() to get all reviews
        model.addAttribute("allReviews", allReviews);
        return "users/all-reviews"; // Remove the redirect and return the correct view name
    }
}



