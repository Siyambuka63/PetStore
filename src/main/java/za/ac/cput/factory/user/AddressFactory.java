package za.ac.cput.factory.user;

import za.ac.cput.domain.user.Address;
import za.ac.cput.domain.user.Type;
import za.ac.cput.util.Helper;

//athenkosi mekana
public class AddressFactory {
    public static Address createAddress(long addressId, String complexDetail, String city, String streetAddress, String suburb, String postalCode, Type type) {
        if (!Helper.isValidPostalCode(postalCode) ||
                Helper.isNullOrEmpty(suburb) ||
                Helper.isNullOrEmpty(city) ||
                type == null ||
                Helper.isNullOrEmpty(streetAddress))
            return null;

        return new Address.Builder()
                .setAddressID(addressId)
                .setCity(city)
                .setStreetAddress(streetAddress)
                .setPostalCode(postalCode)
                .setComplexDetail(complexDetail)
                .setSuburb(suburb)
                .build();
    }
}