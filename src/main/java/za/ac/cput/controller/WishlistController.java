package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.user.wishlist.Wishlist;
import za.ac.cput.domain.user.wishlist.WishlistId;
import za.ac.cput.service.user.impl.WishlistServiceImpl;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistServiceImpl service;

    @Autowired
    public WishlistController(WishlistServiceImpl service) {this.service = service;}

    @PostMapping("/create")
    public Wishlist create(@RequestBody Wishlist wishlist) {
        return service.create(wishlist);
    }

    @GetMapping("/read/{email}/{productId}")
    public Wishlist read(@PathVariable String email, @PathVariable Long productId) {
        return service.read(new WishlistId(email, productId));
    }

    @PutMapping("/update")
    public Wishlist update(@RequestBody Wishlist wishlist) {
        return service.update(wishlist);
    }

    @DeleteMapping("/delete/{email}/{productId}")
    public void delete(@PathVariable String email, @PathVariable Long productId) {
        service.delete(new WishlistId(email, productId));
    }

    @GetMapping("/getAll")
    public List<Wishlist> getAll() {
        return service.getAll();
    }

    @GetMapping("findbyContactEmail/{email}")
    public List<Wishlist> findByContactEmail(@PathVariable String email) { return service.findbyContactEmail(email); }

    @GetMapping("/findbyProductId/{productId}")
    public List<Wishlist> findByProductId(@PathVariable Long productId) { return service.findbyProductId(productId); }
}
