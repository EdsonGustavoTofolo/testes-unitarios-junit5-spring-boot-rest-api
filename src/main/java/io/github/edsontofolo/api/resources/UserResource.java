package io.github.edsontofolo.api.resources;

import io.github.edsontofolo.api.domain.dto.UserDto;
import io.github.edsontofolo.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResource {

    public static final String ID = "/{id}";
    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping(ID)
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

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        var user = this.userService.create(userDto);
        var userSavedDto = this.mapper.map(user, UserDto.class);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(userSavedDto.getId()).toUri();

        return ResponseEntity.created(uri).body(userSavedDto);
    }

    @PutMapping(ID)
    public ResponseEntity<UserDto> update(@PathVariable Integer id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        var user = this.userService.update(userDto);
        var userUpdatedDto = this.mapper.map(user, UserDto.class);

        return ResponseEntity.ok(userUpdatedDto);
    }

    @DeleteMapping(ID)
    public ResponseEntity<UserDto> delete(@PathVariable Integer id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
