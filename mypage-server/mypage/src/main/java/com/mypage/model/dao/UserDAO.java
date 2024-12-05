package com.mypage.model.dao;

import com.mypage.model.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserDAO extends JpaRepository<User, String>, QuerydslPredicateExecutor<User> {
}
