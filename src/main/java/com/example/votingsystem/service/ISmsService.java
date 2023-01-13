package com.example.votingsystem.service;

public interface ISmsService {
    boolean sendVerification(String cellphone,String code);
}
