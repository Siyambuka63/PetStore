package za.ac.cput.service;

import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.review.ReviewId;

import java.util.List;

public interface ReviewService extends IService<Review, ReviewId> {
    List<Review> getAll();
}
