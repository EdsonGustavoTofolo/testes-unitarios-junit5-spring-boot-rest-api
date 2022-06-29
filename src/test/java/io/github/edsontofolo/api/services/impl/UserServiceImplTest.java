package io.github.edsontofolo.api.services.impl;

import io.github.edsontofolo.api.domain.User;
import io.github.edsontofolo.api.domain.dto.UserDto;
import io.github.edsontofolo.api.repositories.UserRepository;
import io.github.edsontofolo.api.services.exceptions.DataIntegratyViolationException;
import io.github.edsontofolo.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final int ID = 1;
    public static final String NAME = "Edson";
    public static final String EMAIL = "edson@gmail.com";
    public static final String PASSWORD = "1234";

    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDto userDto;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        this.startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        // cenario
        when(this.userRepository.findById(ID)).thenReturn(optionalUser);

        // acao
        var response = this.service.findById(ID);

        // verificacao
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        // cenario
        when(this.userRepository.findById(ID)).thenThrow(new ObjectNotFoundException("Usuário não encontrado"));

        // acao
        try {
            this.service.findById(ID);
            fail("Deveria ter lancado excecao ObjectNotFound");
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Usuário não encontrado", ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        // cenario
        when(this.userRepository.findAll()).thenReturn(List.of(this.user));

        // acao
        var users = this.service.findAll();

        // verificacao
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(User.class, users.get(0).getClass());

        assertEquals(ID, users.get(0).getId());
        assertEquals(NAME, users.get(0).getName());
        assertEquals(EMAIL, users.get(0).getEmail());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        // cenario
        when(this.userRepository.save(Mockito.any(User.class))).thenReturn(this.user);
        when(this.mapper.map(this.userDto, User.class)).thenReturn(this.user);

        // acao
        var createdUser = this.service.create(this.userDto);

        // verificacao
        assertNotNull(createdUser);
        assertEquals(User.class, createdUser.getClass());
        assertEquals(ID, createdUser.getId());
        assertEquals(NAME, createdUser.getName());
        assertEquals(EMAIL, createdUser.getEmail());
        assertEquals(PASSWORD, createdUser.getPassword());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        // cenario
        when(this.userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        // acao
        try {
            this.service.create(this.userDto);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        // cenario
        when(this.userRepository.save(Mockito.any(User.class))).thenReturn(this.user);
        when(this.mapper.map(this.userDto, User.class)).thenReturn(this.user);

        // acao
        var updatedUser = this.service.update(this.userDto);

        // verificacao
        assertNotNull(updatedUser);
        assertEquals(User.class, updatedUser.getClass());
        assertEquals(ID, updatedUser.getId());
        assertEquals(NAME, updatedUser.getName());
        assertEquals(EMAIL, updatedUser.getEmail());
        assertEquals(PASSWORD, updatedUser.getPassword());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        // cenario
        when(this.userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        // acao
        try {
            this.service.update(this.userDto);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }
    }

    @Test
    void delete() {
    }

    private void startUser() {
        this.user = new User(ID, NAME, EMAIL, PASSWORD);
        this.userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
        this.optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}