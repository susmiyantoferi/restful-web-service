package com.restful.webservice.service;

import com.restful.webservice.dto.UserDto;
import com.restful.webservice.entity.User;
import com.restful.webservice.repository.UserRepository;
import com.restful.webservice.utils.GenerateUserId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenerateUserId generateUserId;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto create(UserDto requestUserDto) {
        if (userRepository.findByEmail(requestUserDto.getEmail()) != null)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Email already exist");
        User user = new User();
        BeanUtils.copyProperties(requestUserDto, user);

        user.setEncryptedPassword(bCryptPasswordEncoder.encode(requestUserDto.getPassword()));
        user.setUserId(generateUserId.generateId(30));

        User saveUser = userRepository.save(user);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(saveUser, returnValue);
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
