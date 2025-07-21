package za.ac.cput.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.product.Brand;
import za.ac.cput.repository.product.BrandRepository;
import za.ac.cput.service.product.IBrandService;

import java.util.List;

@Service
public class BrandService implements IBrandService {

    private BrandRepository repository;

    @Autowired
    public BrandService(BrandRepository repository) { this.repository = repository; }

    @Override
    public Brand create(Brand brand) {return repository.save(brand); }

    @Override
    public Brand read(Integer brandID) {return repository.getReferenceById(brandID); }

    @Override
    public Brand update(Brand brand) {
        return repository.save(brand);
    }

    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Brand> getAll() {
        return repository.findAll();
    }

    @Override
    public Brand findByBrandName(String brandName) {
        return repository.findByBrandName(brandName);
    }

    @Override
    public Brand findByBrandID(int brandID) {
        return repository.findByBrandID(brandID);
    }

}
