package com.example.votingsystem.service;

import com.example.votingsystem.model.User;

public interface IVerificationCodeService {
    void sendVerification(String cellphone);
    User checkVerification(String cellphone,String code) throws Exception;
}
