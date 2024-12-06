package com.mypage.controller;

import com.mypage.model.vo.User;
import com.mypage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myblog/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("public/user")
    public ResponseEntity adminRegister(@RequestBody User vo) {
        vo.setUserRole(User.UserRole.ADMIN);
        service.createAdmin(vo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping("public/user")
    public ResponseEntity adminLogin(@RequestParam(name = "userId") String userId,
                                     @RequestParam(name = "userPassword") String userPassword) {
        User user = new User();
        user.setUserId(userId);
        user.setUserPassword(userPassword);

        return ResponseEntity.status(HttpStatus.OK).body( service.adminLogin(user));
    }
}
