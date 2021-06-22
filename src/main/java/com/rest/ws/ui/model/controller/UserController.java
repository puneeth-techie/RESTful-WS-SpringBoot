package com.rest.ws.ui.model.controller;

import com.rest.ws.service.UserService;
import com.rest.ws.shared.dto.UserDto;
import com.rest.ws.ui.model.request.UserRequestModel;
import com.rest.ws.ui.model.response.OperationNames;
import com.rest.ws.ui.model.response.OperationResult;
import com.rest.ws.ui.model.response.UserResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserDto userDto;

    @Autowired
    UserResponseModel userResponseModel;

    /**
     * Fetching the User Record using userId.
     * @param userId
     * @return
     */
    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserResponseModel getUser(@PathVariable String userId){
        UserDto user = userService.getUserById(userId);
        BeanUtils.copyProperties(user, userResponseModel);
        return userResponseModel;
    }

    /**
     * Fetching all users.
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserResponseModel> getAllUser(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "25") int limit){
        ArrayList<UserResponseModel> userResponseModels = new ArrayList<>();
        List<UserDto> allUser = userService.getAllUser(page, limit);
        for (UserDto userDto: allUser) {
            BeanUtils.copyProperties(userDto, userResponseModel);
            userResponseModels.add(userResponseModel);
        }
        return userResponseModels;
    }

    /**
     * User Registration.
     * @param userDetails
     * @return
     */
    @PostMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserResponseModel createUser(@RequestBody UserRequestModel userDetails) {
        BeanUtils.copyProperties(userDetails, userDto);
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, userResponseModel);
        return userResponseModel;
    }

    /**
     * Deleting a specific user by their userId.
     * @param userId
     * @return
     */
    @PostMapping(value = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public OperationResult deleteUser(@PathVariable String userId){
        OperationResult operationResult = new OperationResult();
        userService.deleteUser(userId);
        operationResult.setOperationName(OperationNames.DELETE.name());
        operationResult.setOperationStatus(OperationNames.SUCCESS.name());
        return operationResult;
    }
}
