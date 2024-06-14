package com.ra.project_md04.controller;


import com.ra.project_md04.model.dto.request.FormLogin;
import com.ra.project_md04.model.dto.request.FormRegister;
import com.ra.project_md04.model.dto.respone.JWTResponse;
import com.ra.project_md04.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api.myservice.com/v1")
public class AuthenController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> register(@RequestBody FormRegister formRegister) {
        boolean register = userService.registerUser(formRegister);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<JWTResponse> login(@RequestBody FormLogin formLogin) {
        //can ham trong UserService de xac thuc username,password
        return new ResponseEntity<>(userService.loginUser(formLogin), HttpStatus.OK);
    }
}
