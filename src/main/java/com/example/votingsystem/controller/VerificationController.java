package com.example.votingsystem.controller;

import com.example.votingsystem.model.User;
import com.example.votingsystem.service.IVerificationCodeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VerificationController {

    @Autowired
    IVerificationCodeService verificationCodeService;

    @ResponseBody
    @RequestMapping(path= "send",method = RequestMethod.GET)
    public void send(String cellphone){
        verificationCodeService.sendVerification(cellphone);
    }

    @RequestMapping(path = "/checkVerificationCode",method = RequestMethod.POST)
    public String checkVerificationCode(
            @RequestParam(name = "phone")
            String cellphone,
            @RequestParam(name = "code")
            String code, Model model, HttpServletRequest request){
        try {
            User user = verificationCodeService.checkVerification(cellphone, code);
            request.getSession().setAttribute("user",user);
            return "redirect:/votingIndex";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "login";
        }
    }
}
