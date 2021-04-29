package com.example.demo.service;

import com.example.demo.ui.model.request.UserDetailsRequestModel;
import com.example.demo.ui.model.response.UserRest;
import java.util.List;


public interface UserService {
    UserRest creatUser (UserDetailsRequestModel userDetailsRequestModel);
    UserRest getUserByUserId (String userId);
    UserRest updateUser (String userId, UserDetailsRequestModel userDetailsRequestModel);
    List<UserRest> getUsers (int page, int limit);
    void deleteUser (String userId);
}