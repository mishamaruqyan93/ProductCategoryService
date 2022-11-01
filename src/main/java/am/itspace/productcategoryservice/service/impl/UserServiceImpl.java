package am.itspace.productcategoryservice.service.impl;

import am.itspace.productcategoryservice.exception.UserNotFoundException;
import am.itspace.productcategoryservice.model.User;
import am.itspace.productcategoryservice.repository.UserRepository;
import am.itspace.productcategoryservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        if (id <= 0) {
            throw new UserNotFoundException("User does not exists this id - " + id);
        } else {
            Optional<User> user = userRepository.findById(id);
            return user;
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        if (Objects.isNull(user)) {
            throw new NullPointerException("User was passed null");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(int id) {
        if (id <= 0) {
            throw new UserNotFoundException("User does not exists with this id `" + id);
        }
        userRepository.deleteById(id);
    }
}
