package com.example.votingsystem.service.impl;

import com.example.votingsystem.dao.IVerificationCodeDao;
import com.example.votingsystem.model.User;
import com.example.votingsystem.model.VerificationCode;
import com.example.votingsystem.service.ISmsService;
import com.example.votingsystem.service.IUserService;
import com.example.votingsystem.service.IVerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class VerificationCodeService implements IVerificationCodeService {

    @Autowired
    IVerificationCodeDao verificationCodeDao;
    @Autowired
    ISmsService smsService;
    @Autowired
    IUserService userService;

    @Override
    public void sendVerification(String cellphone) {
//        int code = new Random().nextInt(999999);
//        if(code < 100000){
//            code = code + 100000;
//        }
        // todo
        int code = 123456;
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCellphone(cellphone);
        verificationCode.setCode(code + "");
        verificationCode.setSendTime(new Date());
        verificationCode.setStatus(0);
        verificationCodeDao.save(verificationCode);
        smsService.sendVerification(cellphone,code + "");
    }

    @Override
    public User checkVerification(String cellphone, String code) throws Exception{
        List<VerificationCode> verificationCodeList = verificationCodeDao.
                findByCellphoneAndStatusOrderBySendTimeDesc(cellphone, 0);
        if(!verificationCodeList.isEmpty()){
            VerificationCode verificationCode = verificationCodeList.get(0);
            if(new Date().getTime() - verificationCode.getSendTime().getTime() > 5*60*1000){
                throw new Exception("验证码已过期");
            }
            if(code.equals(verificationCode.getCode())){
                for(VerificationCode verificationCode1 : verificationCodeList){
                    verificationCode1.setStatus(1);
                }
                verificationCodeDao.saveAll(verificationCodeList);
                User user = userService.getUserByCellphone(cellphone);
                if(user == null){
                    User user1 = userService.newUser(cellphone);
                    return user1;
                }
                return user;
            }else{
                throw new Exception("验证码错误");
            }
        }else{
            throw new Exception("验证码错误");
        }
    }
}
