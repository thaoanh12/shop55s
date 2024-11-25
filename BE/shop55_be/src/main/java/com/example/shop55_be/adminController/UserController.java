package com.example.shop55_be.adminController;

import com.example.shop55_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/find-by-mail/{mail}")
    public ResponseEntity<?> findByUserLogged(@PathVariable("mail") String mail){
        return new ResponseEntity<>(userService.findByEmail(mail), HttpStatus.OK);
    }
    @GetMapping("/find-by-code/{code}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('STAFF') or hasRole('USER')")
    public ResponseEntity<?> findUserByCode(@PathVariable("code") String code){
        return new ResponseEntity<>(userService.findByCode(code), HttpStatus.OK);
    }
}
