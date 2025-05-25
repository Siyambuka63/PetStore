/*
   Nkheso Senior Mathebula 230762883
*/
package za.ac.cput.factory;

import za.ac.cput.domain.Product;
import za.ac.cput.domain.Wishlist;
import za.ac.cput.util.Helper;

import java.util.List;

public class WishlistFactory {
    public static Wishlist createWishlist(int wishlistId, int userId, List<Product> items) {
        if (Helper.isNullOrEmpty(items))
            return null;

        return new Wishlist.Builder()
                .setWishlistId(wishlistId)
                .setUserId(userId)
                .setItem(items)
                .build();
    }
}
