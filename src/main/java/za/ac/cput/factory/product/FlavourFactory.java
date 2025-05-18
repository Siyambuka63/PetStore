package za.ac.cput.factory.product;

import za.ac.cput.domain.product.Flavour;
import za.ac.cput.util.Helper;

public class FlavourFactory {
    private static Flavour createFlavour(int id, String flavourName) {
        if (Helper.isNullOrEmpty(flavourName)) return null;
        return new Flavour.Builder()
                .setFlavourID(id)
                .setFlavourName(flavourName)
                .build();
    }
}
