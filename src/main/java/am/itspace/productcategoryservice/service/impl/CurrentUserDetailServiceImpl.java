package am.itspace.productcategoryservice.service.impl;

import am.itspace.productcategoryservice.model.User;
import am.itspace.productcategoryservice.repository.UserRepository;
import am.itspace.productcategoryservice.sequrity.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrentUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(s);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new CurrentUser(user.get());
    }
}
