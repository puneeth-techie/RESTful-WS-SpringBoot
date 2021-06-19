package com.rest.ws.service;

import com.rest.ws.shared.dto.UserDto;
import com.rest.ws.ui.model.request.UserRequestModel;
import org.springframework.data.repository.CrudRepository;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
