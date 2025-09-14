package za.ac.cput.service.user;

import org.springframework.data.repository.query.Param;
import za.ac.cput.domain.user.wishlist.Wishlist;
import za.ac.cput.domain.user.wishlist.WishlistId;
import za.ac.cput.service.IService;

import java.util.List;

public interface WishlistService extends IService<Wishlist, WishlistId> {
    List<Wishlist> getAll();

    public List<Wishlist> findbyContactEmail(@Param("email") String email);

    public List<Wishlist> findbyProductId(@Param("productId") long productId);
}
