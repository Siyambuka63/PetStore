package za.ac.cput.factory.user;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.user.Address;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressFactoryTest {
    private static final Address a1 = AddressFactory.createAddress(1234, "flat", "Cape Town", "23739 Nkani Street", "Kraaifontein", "7570");
    private static final Address a2 = AddressFactory.createAddress(3453, "apartment", "Cape Town", "23739 Nkani Street", "Brackenefell", "7570");
    private static final Address a3 = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570");

    @Test
    @Order(1)
    public void testCreateAddress() {
        assertNotNull(a1);
        System.out.println(a1);
    }

    @Test
    @Order(2)
    public void testCreatAddressWithAllAttributes() {
        assertNotNull(a2);
        System.out.println(a2);

    }

    @Test
    @Order(3)

    public void testCreateAddressThatFails() {
        assertNotNull(a3);
        System.out.println(a3);
    }

    @Test
    @Order(4)
    @Disabled
    public void testNotImplementedYet() {

    }
}