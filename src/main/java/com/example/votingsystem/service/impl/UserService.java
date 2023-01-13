package com.example.votingsystem.service.impl;

import com.example.votingsystem.dao.IUserDao;
import com.example.votingsystem.model.User;
import com.example.votingsystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Override
    public User getUserByCellphone(String cellphone) {
        return userDao.findByCellphone(cellphone);
    }

    @Override
    public User newUser(String cellphone) {
        User user = new User();
        String a = "qwertyuipasdfghjkzxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM123456789";
        String name = "";
        Random random = new Random();
        for(int i = 0;i < 9;i++){
            name = name + a.charAt(random.nextInt(a.length() - 1));
        }
        user.setName(name);
        user.setCellphone(cellphone);
        userDao.save(user);
        return user;
    }
}
