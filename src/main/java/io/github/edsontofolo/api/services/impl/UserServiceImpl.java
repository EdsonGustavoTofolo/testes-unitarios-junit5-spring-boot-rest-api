package io.github.edsontofolo.api.services.impl;

import io.github.edsontofolo.api.domain.User;
import io.github.edsontofolo.api.repositories.UserRepository;
import io.github.edsontofolo.api.services.UserService;
import io.github.edsontofolo.api.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> opt = this.userRepository.findById(id);
        return opt.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
