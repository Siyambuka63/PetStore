package za.ac.cput.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.product.FoodType;
import za.ac.cput.repository.product.FoodTypeRepository;
import za.ac.cput.service.product.IFoodTypeService;

import java.util.List;

@Service
public class FoodTypeService implements IFoodTypeService {

    private FoodTypeRepository foodTypeRepo;

    @Autowired
    public FoodTypeService(FoodTypeRepository foodTypeRepo) {this.foodTypeRepo = foodTypeRepo;}

    @Override
    public FoodType create(FoodType foodType) {return foodTypeRepo.save(foodType);}

    @Override
    public FoodType read(Integer foodTypeID) {return foodTypeRepo.getReferenceById(foodTypeID); }

    @Override
    public FoodType update(FoodType foodType) {
        return foodTypeRepo.save(foodType);
    }

    @Override
    public boolean delete(Integer id) {
        foodTypeRepo.deleteById(id);
        return true;
    }

    @Override
    public List<FoodType> getAll() {
        return List.of();
    }

    @Override
    public FoodType findByFoodTypeName(String foodTypeName) {
        return null;
    }

    @Override
    public FoodType findByFoodTypeID(Integer foodTypeID) {
        return null;
    }
}
