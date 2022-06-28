package io.github.edsontofolo.api.resources;

import io.github.edsontofolo.api.domain.dto.UserDto;
import io.github.edsontofolo.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id) {
        var user = this.userService.findById(id);
        var userDto = this.mapper.map(user, UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        var list = this.userService.findAll();
        var listDto = list.stream().map(u -> this.mapper.map(u, UserDto.class)).toList();
        return ResponseEntity.ok(listDto);
    }
}
