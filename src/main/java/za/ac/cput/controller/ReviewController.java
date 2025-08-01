/*
 * Nkheso Senior Mathebula 230762883
 */
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Review;
import za.ac.cput.domain.ReviewId;
import za.ac.cput.service.user.impl.ReviewServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewServiceImpl service;


        @Autowired
        public ReviewController(ReviewServiceImpl service) {
            this.service = service;
        }

    @PostMapping("/create")
    public Review create(@RequestBody Review review) {
        return service.save(review);
    }

    @PutMapping("/update")
    public Review update(@RequestBody Review review) {
        return service.save(review);
    }

    @DeleteMapping("/delete/{userId}/{productId}")
    public void delete(@PathVariable Long userId, @PathVariable Long productId) {
        service.delete(new ReviewId(userId, productId));
    }

    @GetMapping("/read/{userId}/{productId}")
    public Review read(@PathVariable Long userId, @PathVariable Long productId) {
        return service.read(new ReviewId(userId, productId));
    }

    @GetMapping("/getAll")
    public List<Review> getAll() {
        return service.findAll();
    }}
