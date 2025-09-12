package za.ac.cput.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.user.wishlist.Wishlist;
import za.ac.cput.domain.user.wishlist.WishlistId;
import za.ac.cput.repository.user.WishlistRepository;
import za.ac.cput.service.user.WishlistService;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private static WishlistService service;

    @Autowired
    private WishlistRepository repository;

    @Override
    public Wishlist create(Wishlist wishlist) { return repository.save(wishlist); }

    @Override
    public Wishlist read(WishlistId wishlistId) { return repository.findById(wishlistId).orElse(null); }

    @Override
    public Wishlist update(Wishlist wishlist) { return repository.save(wishlist); }

    @Override
    public void delete(WishlistId wishlistId) { repository.deleteById(wishlistId); }

    @Override
    public List<Wishlist> getAll() { return repository.findAll(); }

    @Override
    public List<Wishlist> findbyContactEmail(String email) { return repository.findbyContactEmail(email); }

    @Override
    public List<Wishlist> findbyProductId(long productId) { return repository.findbyProductId(productId); }
}
