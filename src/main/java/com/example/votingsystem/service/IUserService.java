package com.example.votingsystem.service;

import com.example.votingsystem.model.User;

public interface IUserService {
    User getUserByCellphone(String cellphone);
    User newUser(String cellphone);
}
