package com.restful.webservice.controller;

import com.restful.webservice.dto.UserDto;
import com.restful.webservice.model.request.CreateUserRequest;
import com.restful.webservice.model.response.UserResponse;
import com.restful.webservice.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String get(){
        return "get user";
    }

    @PostMapping
    public UserResponse create(@RequestBody CreateUserRequest request){
        UserResponse userResponse = new UserResponse();
        
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(request, userDto);

        UserDto createUser = userService.create(userDto);
        BeanUtils.copyProperties(createUser, userResponse);

        return userResponse;
    }
}
