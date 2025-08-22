package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.review.ReviewId;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
}