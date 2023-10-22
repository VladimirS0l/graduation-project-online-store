package ru.service.user.usersservice.service;

import ru.service.user.usersservice.model.User;

public interface UserService {
    User getById(Long id);
    User getByUsername(String username);
    User update(Long id, User user);
    User create(User user);
    void delete(Long id);
}
