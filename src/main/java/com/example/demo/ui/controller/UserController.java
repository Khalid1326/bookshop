package com.example.demo.ui.controller;

import com.example.demo.exeptions.ServiceException;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;
import com.example.demo.ui.model.request.UserDetailsRequestModel;
import com.example.demo.ui.model.response.ErrorMessages;
import com.example.demo.ui.model.response.OperationStatusModel;
import com.example.demo.ui.model.response.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id ){
        UserRest returnValue = userService.getUserByUserId(id);
        return returnValue;
    }

    @PostMapping()
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails){

        if (userDetails.getFirstName().isEmpty()) {
            throw new ServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage().toString());
        }

        UserRest createdUser = userService.creatUser(userDetails);
        return createdUser;
    }
    @PutMapping(path = "/{id}")
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails){
        UserRest updatedUser = userService.updateUser(id, userDetails);
        return updatedUser;
    }
    @GetMapping()
    public List<UserRest> getUsers(@RequestParam(value ="page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "25") int limit){
        List<UserRest> users = userService.getUsers(page, limit);
        return users;
    }
    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOpertaionName("DELETE");
        userService.deleteUser(id);
        returnValue.setOpertaionResult("SUCCESS");
        return returnValue;
    }
}