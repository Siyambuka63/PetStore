package za.ac.cput.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.cput.domain.user.wishlist.Wishlist;
import za.ac.cput.domain.user.wishlist.WishlistId;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, WishlistId> {
    @Query("SELECT u FROM Wishlist u WHERE u.id.contactId = :email")
    public List<Wishlist> findbyContactEmail(@Param("email") String email);

    @Query("SELECT u FROM Wishlist u WHERE u.id.productId = :productId")
    public List<Wishlist> findbyProductId(@Param("productId") long productId);
}
