package za.ac.cput.controller;
/*Oluhle Makhaye
 * 222419636*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Product;
import za.ac.cput.service.impl.ProductServiceImpl;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductServiceImpl service;

    @Autowired
    public ProductController(ProductServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    @GetMapping("/read/{id}")
    public Product read(@PathVariable long id) {
        return service.read(id);
    }

    @PostMapping("/update")
    public Product update(@RequestBody Product product) {
        return service.update(product);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) { service.delete(id); }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = service.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/findByProductName/{productName}")
    public Product findByProductName(@PathVariable String productName) { return this.service.findByProductName(productName); }
}
