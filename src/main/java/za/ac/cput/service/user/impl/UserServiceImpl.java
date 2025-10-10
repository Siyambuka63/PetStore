package za.ac.cput.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.user.User;
import za.ac.cput.domain.user.UserPrincipal;
import za.ac.cput.repository.user.UserRepository;
import za.ac.cput.service.user.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private static UserService service;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UserRepository repository;

    @Autowired
    private JWTService jwtService;

    @Override
    public User create(User user) {
        User passwordEncodedUser = new User.Builder()
                .copy(user)
                .setPassword(encoder.encode(user.getPassword()))
                .build();
        return repository.save(passwordEncodedUser); }

    @Override
    public User read(String email) { return repository.findById(email).orElse(null); }

    @Override
    public User update(User user) { return repository.save(user); }

    @Override
    public void delete(String email) { repository.deleteById(email); }

    @Override
    public List<User> getAll() { return repository.findAll(); }

    @Override
    public User login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    @Override
    public String verify(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(email);
        }
        return "Invalid user credentials";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = read(username);

        if (user == null) throw new UsernameNotFoundException("user not found");

        return new UserPrincipal(user);
    }
}
