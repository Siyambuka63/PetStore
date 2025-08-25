/*
 * Nkheso Senior Mathebula 230762883
 */
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.review.ReviewId;
import za.ac.cput.service.impl.ReviewServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewServiceImpl service;

    @Autowired
    public ReviewController(ReviewServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Review create(@RequestBody Review review) {
        return service.create(review);
    }

    @GetMapping("/read/{userId}/{productId}")
    public Review read(@PathVariable Long userId, @PathVariable Long productId) {
        return service.read(new ReviewId(userId, productId));
    }

    @PostMapping("/update")
    public Review update(@RequestBody Review review) {
        return service.update(review);
    }

    @DeleteMapping("/delete/{userId}/{productId}")
    public void delete(@PathVariable Long userId, @PathVariable Long productId) {
        service.delete(new ReviewId(userId, productId));
    }

    @GetMapping("/getAll")
    public List<Review> getAll() {
        return service.getAll();
    }
}
