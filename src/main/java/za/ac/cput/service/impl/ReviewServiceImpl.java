// Nkheso Mathebula - 230762883
package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.review.ReviewId;
import za.ac.cput.repository.ReviewRepository;
import za.ac.cput.service.ReviewService;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private static ReviewService service;

    private ReviewRepository repository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Review create(Review review) { return repository.save(review); }

    @Override
    public Review read(ReviewId id) { return repository.getReferenceById(id);}

    @Override
    public Review update(Review review) {return repository.save(review);}

    @Override
    public void delete(ReviewId id) {
        repository.deleteById(id);
    }

    @Override
    public List<Review> getAll() {return repository.findAll();}
}