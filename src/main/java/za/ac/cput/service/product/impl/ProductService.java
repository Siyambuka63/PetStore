package za.ac.cput.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.product.Product;
import za.ac.cput.repository.product.ProductRepository;
import za.ac.cput.service.product.IProductService;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) { this.repository = repository; }

    @Override
    public Product create(Product product) { return repository.save(product); }

    @Override
    public Product read(Integer id) { return repository.getReferenceById(id); }

    @Override
    public Product update(Product product) { return repository.save(product); }

    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }

    public List<Product> getAll() { return repository.findAll(); }

    @Override
    public Product findByProductName(String productName) {
        return repository.findByProductName(productName);
    }

    @Override
    public Product findByProductID(Integer productID) {
        return repository.findByProductID(productID);
    }

}
