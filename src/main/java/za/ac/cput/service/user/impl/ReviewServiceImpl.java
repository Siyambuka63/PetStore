// Nkheso Mathebula - 230762883
package za.ac.cput.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.ReviewId;
import za.ac.cput.repository.ReviewRepository;
import za.ac.cput.service.ReviewService;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository repository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Review save(Review review) {
        return repository.save(review);
    }

    @Override
    public Review read(ReviewId id) {
        Optional<Review> review = repository.findById(id);
        return review.orElse(null);
    }

    @Override
    public void delete(ReviewId id) {
        repository.deleteById(id);
    }

    @Override
    public List<Review> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Review> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Review> findByProductId(Long productId) {
        return repository.findByProductId(productId);
    }
}
