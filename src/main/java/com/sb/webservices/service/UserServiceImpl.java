package com.sb.webservices.service;

import com.sb.webservices.model.UserRequest;
import com.sb.webservices.model.UserResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class UserServiceImpl implements  UserService{

    Map<String, UserResponse> users;
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(userRequest.getFirstName());
        userResponse.setLastName(userRequest.getLastName());
        userResponse.setEmail(userRequest.getEmail());
        userResponse.setUserId("raja");

        if(null == users){
            users = new HashMap<>();
            users.put("raja", userResponse);
        }
        return userResponse;
    }
}
