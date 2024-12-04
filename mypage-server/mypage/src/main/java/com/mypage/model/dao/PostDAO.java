package com.mypage.model.dao;

import com.mypage.model.vo.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDAO extends JpaRepository<Post, Integer> {
}
