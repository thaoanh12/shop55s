package com.example.shop55_be.appController;

import com.example.shop55_be.dto.LoginDto;
import com.example.shop55_be.service.AuthenticationService;
import com.example.shop55_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;

    @GetMapping("/view-login")
    public String viewLogin(Model model){
        model.addAttribute("account",new LoginDto());
        return "Login";
    }

}
