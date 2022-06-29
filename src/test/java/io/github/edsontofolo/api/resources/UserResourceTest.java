package io.github.edsontofolo.api.resources;

import io.github.edsontofolo.api.domain.User;
import io.github.edsontofolo.api.domain.dto.UserDto;
import io.github.edsontofolo.api.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserResourceTest {

    public static final int ID = 1;
    public static final String NAME = "Edson";
    public static final String EMAIL = "edson@gmail.com";
    public static final String PASSWORD = "1234";

    @InjectMocks
    private UserResource resource;
    @Mock
    private UserService userService;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        this.startUser();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        // cenario
        when(this.userService.findById(anyInt())).thenReturn(this.user);
        when(this.mapper.map(this.user, UserDto.class)).thenReturn(this.userDto);

        // acao
        ResponseEntity<UserDto> response = resource.findById(ID);

        // verificacao
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        this.user = new User(ID, NAME, EMAIL, PASSWORD);
        this.userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
    }
}