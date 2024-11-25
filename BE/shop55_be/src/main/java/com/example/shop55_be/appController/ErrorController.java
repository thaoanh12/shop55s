package com.example.shop55_be.appController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/403error")
    public String ForbidenPage(){
        return "403ErrorPage";
    }
}
