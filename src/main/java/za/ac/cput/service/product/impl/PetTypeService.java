package za.ac.cput.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.product.PetType;
import za.ac.cput.repository.product.FoodTypeRepository;
import za.ac.cput.repository.product.PetTypeRepository;
import za.ac.cput.service.product.IPetTypeService;

import java.util.List;

@Service
public class PetTypeService implements IPetTypeService {

    private PetTypeRepository petTypeRepo;

    @Autowired
    public PetTypeService(FoodTypeRepository foodTypeRepo) {this.petTypeRepo = petTypeRepo;}

    @Override
    public PetType create(PetType petType) {return petTypeRepo.save(petType);}

    @Override
    public PetType read(Integer petTypeID) {return petTypeRepo.getReferenceById(petTypeID); }

    @Override
    public PetType update(PetType petType) {
        return petTypeRepo.save(petType);
    }

    @Override
    public boolean delete(Integer id) {
        petTypeRepo.deleteById(id);
        return true;
    }

    @Override
    public List<PetType> getAll() {
        return List.of();
    }

    @Override
    public PetType findByPetTypeName(String petTypeName) {
        return null;
    }

    @Override
    public PetType findByPetTypeID(Integer petTypeID) {
        return null;
    }
}
