package za.ac.cput.service.product;

import za.ac.cput.domain.product.FoodType;
import za.ac.cput.service.IService;

import java.util.List;

public interface IFoodTypeService extends IService<FoodType, Integer> {
    List<FoodType> getAll();

    FoodType findByFoodTypeName(String foodTypeName);

    FoodType findByFoodTypeID(Integer foodTypeID);
}
