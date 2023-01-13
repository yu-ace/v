package com.example.votingsystem.service.impl;

import com.example.votingsystem.service.ISmsService;
import org.springframework.stereotype.Service;

@Service
public class SmsService implements ISmsService {


    @Override
    public boolean sendVerification(String cellphone, String code) {
        System.out.println(code);
        return true;
    }
}
