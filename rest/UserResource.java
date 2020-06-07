package ru.geekbrains.rest;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.ErrorDto;
import ru.geekbrains.persist.entity.User;
import ru.geekbrains.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}/id")
    public User findById(@PathVariable("id") long id) {

        return userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("ID found in the create request");
        }
        userService.save(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.save(user);
    }

    @DeleteMapping(path = "/{id}/id")
    public void deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleException(UserNotFoundException exc) {
        ErrorDto userErrorResponse = new ErrorDto();
        userErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        userErrorResponse.setMessage(exc.getMessage());
        userErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
