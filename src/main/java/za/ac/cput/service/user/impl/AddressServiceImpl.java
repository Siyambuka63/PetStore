package za.ac.cput.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.user.Address;
import za.ac.cput.repository.user.AddressRepository;
import za.ac.cput.service.user.AddressService;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private static AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address read(Long addressId) {
        return addressRepository.findById(addressId).orElse(null);
    }

    @Override
    public Address update(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void delete(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public List<Address> findByPostalCode(String postalCode) {
        return addressRepository.findByPostalCode(postalCode);
    }
}
