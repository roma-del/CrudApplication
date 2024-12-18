package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public void changeUser(User user);
    List<User> getUsers();
    void addUser(User user);
    void deleteUser(int id);
}
