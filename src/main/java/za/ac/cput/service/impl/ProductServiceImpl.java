package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private static ProductService service;

    private ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(Product product) {return repository.save(product);}

    @Override
    public Product read(Long id) {return repository.getReferenceById(id);}

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Long id) {repository.deleteById(id);}

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product findByProductName(String productName) {
        return repository.findByProductName(productName);
    }
}