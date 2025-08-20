package za.ac.cput.service.user;

import za.ac.cput.domain.user.Address;


import java.util.List;

public interface AddressService {
    Address create(Address address);

    Address read(Long addressId);

    Address update(Address address);

    void delete(Long addressId);

    List<Address> getAll();

    Address findByAddressId(Long addressId);

    Address findByPostalCode(String postalCode);
}
