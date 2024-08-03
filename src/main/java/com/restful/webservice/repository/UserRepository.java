package com.restful.webservice.repository;

import com.restful.webservice.dto.UserDto;
import com.restful.webservice.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
