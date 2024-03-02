package com.sb.webservices.service;

import com.sb.webservices.model.UserRequest;
import com.sb.webservices.model.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);
}
