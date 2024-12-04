package com.mypage.model.dao;

import com.mypage.model.vo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.categoryTitle = :categoryTitle")
    Category findByCategoryTitle(@Param("categoryTitle")String categoryTitle);
}
