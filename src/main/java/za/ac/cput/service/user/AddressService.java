package za.ac.cput.service.user;

import za.ac.cput.domain.user.Address;
import za.ac.cput.service.IService;

import java.util.List;

public interface AddressService extends IService<Address, Long> {
    List<Address> getAll();

    List<Address> findByPostalCode(String postalCode);
}