package com.rest.ws.ui.model.controller;

import com.rest.ws.service.UserService;
import com.rest.ws.shared.dto.UserDto;
import com.rest.ws.ui.model.request.UserRequestModel;
import com.rest.ws.ui.model.response.UserResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public UserResponseModel createUser(@RequestBody UserRequestModel userDetails) {
        UserResponseModel userResponseModel = new UserResponseModel();
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails, userDto);
        UserDto createdUser = userService.createUser(userDto);

        BeanUtils.copyProperties(createdUser, userResponseModel);
        return userResponseModel;
    }
}
