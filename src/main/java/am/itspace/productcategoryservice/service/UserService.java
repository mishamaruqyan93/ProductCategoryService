package am.itspace.productcategoryservice.service;

import am.itspace.productcategoryservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(int id);

    Optional<User> findUserByEmail(String email);

    User save(User user);

    void deleteUserById(int id);
}
