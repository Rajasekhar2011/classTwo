package com.sb.webservices.controller;

import com.sb.webservices.exception.UserNotFoundException;
import com.sb.webservices.model.UserRequest;
import com.sb.webservices.model.UserResponse;
import com.sb.webservices.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    Map<String, UserResponse> users;

    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId){

        //String firstName=null;
        //int length = firstName.length();
        if(users.containsKey(userId)){
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        }else {
            throw new UserNotFoundException("No user found for userid ->"+userId);
        }
    }

    @GetMapping("/")
    public String getUser(@RequestParam(value = "page", required = true, defaultValue = "2") int page, @RequestParam(value = "limit", required = false, defaultValue = "40") int limit){

        return "Get user is called for page number ->"+page+" and limit for this page is "+limit;
    }

    @PostMapping(value = "/",consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
              MediaType.APPLICATION_XML_VALUE,
              MediaType.APPLICATION_JSON_VALUE
            }
    )
    public  ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.createUser(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}" ,
            consumes = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            },
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            }
    )
    public  ResponseEntity<UserResponse> updateUser(@PathVariable String userId , @RequestBody UserRequest userRequest){
        if(users.containsKey(userId)){
            UserResponse userResponse = users.get(userId);
            userResponse.setFirstName(userRequest.getFirstName());
            userResponse.setLastName(userRequest.getLastName());
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        UserResponse user = users.get(userId);
        if(null != user){
            users.remove(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
