package za.ac.cput.service;

import za.ac.cput.domain.Product;

import java.util.List;

public interface ProductService extends IService<Product, Long> {
    List<Product> getAll();

    Product findByProductName(String productName);

  //  Product findByProductID(Long productID);
}
