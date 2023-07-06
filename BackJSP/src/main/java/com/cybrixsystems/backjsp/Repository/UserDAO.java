package com.cybrixsystems.backjsp.Repository;

import com.cybrixsystems.backjsp.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    boolean loginUser(User user);
    User saveUser(User user);
    boolean updateUser(User user,String emailActualOrNew);
    void deleteUserByEmail(String email);
}
