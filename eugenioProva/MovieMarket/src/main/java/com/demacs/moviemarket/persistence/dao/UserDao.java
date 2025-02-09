package com.demacs.moviemarket.persistence.dao;

import com.demacs.moviemarket.persistence.model.User;

import java.util.List;

public interface UserDao {
    User findById(String id);
    User findByEmail(String email);
    List<User> findAll();
    Boolean insert(User user);
    void update(User user);
    void delete(User user);
    void savePassword(String password);
}