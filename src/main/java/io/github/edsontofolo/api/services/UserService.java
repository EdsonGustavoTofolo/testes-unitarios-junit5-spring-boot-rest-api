package io.github.edsontofolo.api.services;

import io.github.edsontofolo.api.domain.User;

public interface UserService {
    User findById(Integer id);
}
