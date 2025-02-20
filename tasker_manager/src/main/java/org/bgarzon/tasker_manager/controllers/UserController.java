package org.bgarzon.tasker_manager.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bgarzon.tasker_manager.entities.User;
import org.bgarzon.tasker_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                                        .stream()
                                        .map(error -> "Field '" + error.getField() + "' " + error.getDefaultMessage())
                                        .collect(Collectors.toList());
            response.put("message", "Errors while validating user's info");
            response.put("error", errors);
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user)); 
        } catch (Exception e) {
            response.put("message", "Error while registaring user");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }       

}
