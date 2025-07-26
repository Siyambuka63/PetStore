package za.ac.cput.service.user;

import za.ac.cput.domain.Review;
import za.ac.cput.domain.ReviewId;
import za.ac.cput.service.IService;

import java.util.List;

public interface ReviewService extends IService<Review, ReviewId> {
    List<Review> findByUserId(Long userId);
    List<Review> findByProductId(Long productId);
}
