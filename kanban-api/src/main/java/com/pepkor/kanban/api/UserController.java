package com.pepkor.kanban.api;

import com.pepkor.kanban.model.UserDto;
import com.pepkor.kanban.exceptions.ResourceNotFoundException;
import com.pepkor.kanban.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> userDtos = userService.getAllUsers();
        if (userDtos.isEmpty()) {
            logger.error("Users not found");
            throw new ResourceNotFoundException("No users found");
        }
        logger.debug("Found {} users", userDtos.size());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);

        if (userDto == null) {
            logger.error("User not found");
            throw new ResourceNotFoundException("User not found - id " + id);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
        UserDto savedUser = userService.saveUser(userDto);
        logger.debug("Saved user id: ", savedUser.getId());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
