package com.rest.ws.service;

import com.rest.ws.shared.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUser(String userId);
}
