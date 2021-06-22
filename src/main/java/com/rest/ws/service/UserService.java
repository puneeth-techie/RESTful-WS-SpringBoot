package com.rest.ws.service;

import com.rest.ws.shared.dto.UserDto;
import com.rest.ws.ui.model.response.OperationResult;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUser(String email);
    UserDto getUserById (String userId);
    void deleteUser(String userId);
}
