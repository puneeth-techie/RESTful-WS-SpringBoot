package com.rest.ws.ui.model.controller;

import com.rest.ws.service.UserService;
import com.rest.ws.shared.dto.UserDto;
import com.rest.ws.ui.model.request.UserRequestModel;
import com.rest.ws.ui.model.response.UserResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserDto userDto;

    @Autowired
    UserResponseModel userResponseModel;

    @GetMapping("/{userId}")
    public UserResponseModel getUser(@PathVariable String userId){
        UserDto user = userService.getUserById(userId);
        BeanUtils.copyProperties(user, userResponseModel);
        return userResponseModel;
    }

    @PostMapping
    public UserResponseModel createUser(@RequestBody UserRequestModel userDetails) {
        BeanUtils.copyProperties(userDetails, userDto);
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, userResponseModel);
        return userResponseModel;
    }
}
