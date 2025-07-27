package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.ReviewId;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
    List<Review> findByUserId(Long userId);
    List<Review> findByProductId(Long productId);
}
