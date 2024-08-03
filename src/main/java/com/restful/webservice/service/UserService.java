package com.restful.webservice.service;

import com.restful.webservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    UserDto create(UserDto userDto);
}
