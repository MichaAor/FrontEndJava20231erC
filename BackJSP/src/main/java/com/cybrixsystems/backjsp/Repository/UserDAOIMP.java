package com.cybrixsystems.backjsp.Repository;

import com.cybrixsystems.backjsp.Config.DBConnection;
import com.cybrixsystems.backjsp.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOIMP implements UserDAO {
    private final Connection dbCon = DBConnection.getConnection();


    @Override
    public List<User> findAllUsers() {
        List<User> usersDB = new ArrayList<>();
        try {
            ResultSet rs = dbCon.prepareStatement("SELECT * FROM user").executeQuery();
            while(rs.next()){
                usersDB.add(
                new User(rs.getLong("id"),rs.getString("name")
                                    ,rs.getString("surname"),rs.getString("dni")
                                    ,rs.getString("email"),rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersDB;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        Optional<User> usersDB = Optional.empty();
        try {
            PreparedStatement ps = dbCon.prepareStatement("SELECT * FROM user where id = ?");
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                usersDB = Optional.of(
                new User(rs.getLong("id"),rs.getString("name")
                        ,rs.getString("surname"),rs.getString("dni")
                        ,rs.getString("email"),rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersDB;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<User> userDB = Optional.empty();
        try {
            PreparedStatement ps = dbCon.prepareStatement("SELECT * FROM user WHERE email = ?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                userDB = Optional.of(new User(rs.getLong("id"),rs.getString("name")
                        ,rs.getString("surname"),rs.getString("dni")
                        ,rs.getString("email"),rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDB;
    }

    @Override
    public boolean loginUser(User user) {
        Optional<User> userOpt = this.findUserByEmail(user.getEmail());
        if(userOpt.isPresent()){
            return (
                    (userOpt.orElseThrow().getEmail().compareTo(user.getEmail()) == 0)
                        &&
                    (userOpt.orElseThrow().getPassword().compareTo(user.getPassword()) == 0)
                    );
        }
        return false;
    }

    @Override
    public User saveUser(User user) {
        User userSaved = null;
        if(user.getDni() != null && user.getDni().length() <= 8) {
            try(PreparedStatement ps = dbCon.prepareStatement("INSERT INTO user(name,surname,dni,email,password) "
                    + "VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.setString(3, user.getDni());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getPassword());
                int affectedRows = ps.executeUpdate();
                if(affectedRows != 0){
                    ResultSet generateKey = ps.getGeneratedKeys();
                    if(generateKey.next()){
                        user.setId(generateKey.getLong(1));
                        userSaved = user;
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userSaved;
    }

    @Override
    public boolean updateUser(User user,String emailActualOrNew) {
        int n = 0;
        try {
            PreparedStatement ps = dbCon.prepareStatement("UPDATE user SET name=?, surname=?, dni=?" +
                                                                    ",email=?, password=? WHERE email=?");
            ps.setString(1,user.getName());
            ps.setString(2,user.getSurname());
            ps.setString(3,user.getDni());
            ps.setString(4,emailActualOrNew);
            ps.setString(5,user.getPassword());
            ps.setString(6,user.getEmail());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (n != 0);
    }

    @Override
    public void deleteUserByEmail(String email) {
        try {
            PreparedStatement ps = dbCon.prepareStatement("DELETE FROM user where email = ?");
            ps.setString(1,email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
