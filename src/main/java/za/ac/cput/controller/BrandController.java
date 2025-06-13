package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.product.Brand;
import za.ac.cput.service.product.impl.BrandService;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    private BrandService service;

    @Autowired
    public BrandController(BrandService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Brand create(@RequestBody Brand brand) {
        return service.create(brand);
    }

    @GetMapping("/read/{id}")
    public Brand read(@PathVariable int id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Brand update(@RequestBody Brand brand) {
        return service.update(brand);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable int id) {
        return service.delete(id);
    }

    @GetMapping("/getAll")
    public List<Brand> getAll() {
        return service.getAll();
    }

    @GetMapping("/findByBrandName/{brandName}")
    public Brand findByBrandName(@PathVariable String brandName) { return this.service.findByBrandName(brandName); }

}