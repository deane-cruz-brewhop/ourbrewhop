package com.example.codeupspringcapstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.codeupspringcapstone.models.AvatarImage;
import java.util.Optional;

public interface AvatarImageRepository extends JpaRepository<AvatarImage, Long> {

    // Method to find an avatar image by the associated user ID
    Optional<AvatarImage> findByUserId(Long userId);

    // Other methods as needed
}

