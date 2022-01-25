package io.github.gabrielhgcamargo.controller;


import io.github.gabrielhgcamargo.model.UserModel;
import io.github.gabrielhgcamargo.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Registering a new user to use methods in API")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User created"),
            @ApiResponse(code = 400, message = "Validation Error")
    }
    )
    public UserModel save(@RequestBody @Valid @ApiParam("Insert login/username, password, and privilegies(True to Teacher/False to Student)") UserModel user){
        String passwordEncrypted = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypted);
        return userService.save(user);
    }
}
