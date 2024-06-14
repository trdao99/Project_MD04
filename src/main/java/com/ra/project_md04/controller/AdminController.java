package com.ra.project_md04.controller;

import com.ra.project_md04.model.entity.Users;
import com.ra.project_md04.service.impl.RoleService;
import com.ra.project_md04.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api.myservice.com/v1/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService userRoleService;
    @GetMapping("/search/{username}")
    public ResponseEntity<?> user(@PathVariable String username) {
        return new ResponseEntity<>(userService.findUserByFullName(username), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<?> roles() {
        return new ResponseEntity<>(userRoleService.getAllRoles(), HttpStatus.OK);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> changeStatus(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.changeStatus(userId), HttpStatus.OK);
    }
    @GetMapping("/users")
    public ResponseEntity<?> listStudent(@RequestParam(defaultValue = "")String name,
                              @RequestParam(defaultValue = "1")Integer page,
                              @RequestParam(defaultValue = "2")Integer itemPage,
                              @RequestParam(defaultValue = "fullName")String sortBy,
                              @RequestParam(defaultValue = "DESC")String direction){
        Page<Users> userPaging = userService.getUserPaging(name, page - 1, itemPage, sortBy, direction);
        return new ResponseEntity<>(userPaging.getContent(), HttpStatus.OK);
    }
}

