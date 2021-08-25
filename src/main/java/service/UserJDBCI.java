package service;

import Entity.User;

import java.util.List;

public interface UserJDBCI {
    List<User> getAllUsers();
}
