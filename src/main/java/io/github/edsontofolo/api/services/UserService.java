package io.github.edsontofolo.api.services;

import io.github.edsontofolo.api.domain.User;
import io.github.edsontofolo.api.domain.dto.UserDto;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    User create(UserDto userDto);
}
