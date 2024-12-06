package com.mypage.service;

import com.mypage.config.TokenProvider;
import com.mypage.model.dao.UserDAO;
import com.mypage.model.vo.QUser;
import com.mypage.model.vo.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserDAO dao;

    @Autowired
    private JPAQueryFactory factory;

    @Autowired
    private PasswordEncoder pwencoder;

    public void createAdmin(User user) {
        user.setUserPassword(pwencoder.encode(user.getUserPassword()));
        dao.save(user);
    }

    public Map<String, User.UserRole> adminLogin(User user) {
        String token = "";
        if (isAdmin(user)) {
            token = tokenProvider.create(existAdmin(user.getUserId()));
        }
        Map<String, User.UserRole> responseMap = new HashMap<>();
        responseMap.put(token, existAdmin(user.getUserId()).getUserRole());
        return responseMap;
    }
    private User existAdmin(String userId) {
        return dao.getReferenceById(userId);
    }
    public boolean isAdmin(User user) {
        User loginUser = existAdmin(user.getUserId());

        return pwencoder.matches(user.getUserPassword(), loginUser.getUserPassword())
                && loginUser.getUserRole().equals(User.UserRole.ADMIN);
    }
}
