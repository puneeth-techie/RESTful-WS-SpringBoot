package com.rest.ws.ui.model.controller;

import com.rest.ws.service.UserService;
import com.rest.ws.shared.dto.UserDto;
import com.rest.ws.ui.model.request.UserRequestModel;
import com.rest.ws.ui.model.response.OperationNames;
import com.rest.ws.ui.model.response.OperationResult;
import com.rest.ws.ui.model.response.UserResponseModel;
import org.modelmapper.ModelMapper;
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

//    @Autowired
//    UserDto userDto;
//
//    @Autowired
//    UserResponseModel userResponseModel;

    ModelMapper modelMapper = new ModelMapper();

    /**
     * Fetching the User Record using userId.
     * @param userId
     * @return
     */
    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserResponseModel getUser(@PathVariable String userId){
        UserDto user = userService.getUserById(userId);
        UserResponseModel response = modelMapper.map(user, UserResponseModel.class);
        return response;
    }

    /**
     * Fetching all users.
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserResponseModel> getAllUser(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "25") int limit){
        ArrayList<UserResponseModel> userResponseModels = new ArrayList<>();
        List<UserDto> allUser = userService.getAllUser(page, limit);
        for (UserDto userDto: allUser) {
            UserResponseModel response = modelMapper.map(userDto, UserResponseModel.class);
            userResponseModels.add(response);
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
        //BeanUtils.copyProperties(userDetails, userDto);
        UserDto userMapped = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = userService.createUser(userMapped);
        UserResponseModel response = modelMapper.map(createdUser, UserResponseModel.class);
        return response;
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
