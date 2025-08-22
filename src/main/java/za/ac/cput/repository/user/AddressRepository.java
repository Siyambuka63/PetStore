package za.ac.cput.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.user.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

Address findByAddressId(Long addressId);
Address findByPostalCode(String postalCode);
}
