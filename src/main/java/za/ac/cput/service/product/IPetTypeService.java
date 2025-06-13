package za.ac.cput.service.product;

import za.ac.cput.domain.product.PetType;
import za.ac.cput.service.IService;

import java.util.List;

public interface IPetTypeService extends IService<PetType, Integer> {
    List<PetType> getAll();

    PetType findByPetTypeName(String petTypeName);

    PetType findByPetTypeID(Integer petTypeID);
}
