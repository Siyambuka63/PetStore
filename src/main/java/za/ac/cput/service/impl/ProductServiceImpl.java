package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.service.ProductService;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

//    @Autowired
//    private ProductService service;

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(Product product) {return repository.save(product);}

    public Product createProduct(Product product, MultipartFile file) {
        product.setProductName(file.getOriginalFilename());
        product.setImageType(file.getContentType());
        try {
            product.setImageData(file.getBytes());
        } catch (IOException e) {
            return null;
        }

        return repository.save(product);
    }

    @Override
    public Product read(Long id) {return repository.findById(id).orElse(null);}

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