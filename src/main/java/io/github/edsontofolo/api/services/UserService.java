package io.github.edsontofolo.api.services;

import io.github.edsontofolo.api.domain.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
}
