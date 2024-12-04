package com.mypage.service;

import com.mypage.model.dao.CategoryDAO;
import com.mypage.model.dao.PostDAO;
import com.mypage.model.vo.Category;
import com.mypage.model.vo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentsService {

    @Autowired
    private CategoryDAO cDao;

    @Autowired
    private PostDAO pDao;

    public List<Category> allCategory() {
       return cDao.findAll();
    }

    public void createCategory(Category vo, int parentId) {
        Category root = Category.builder()
                .categoryTitle("전체목록")
                .isRoot(true)
                .parent(null)
                .build();
        if(allCategory() == null || allCategory().isEmpty()) {
            cDao.save(root);
        } else {
            root = cDao.findByCategoryTitle("전체목록");
        }
        if(vo.getParent() == null) {
            cDao.save(Category.builder()
                            .categoryTitle(vo.getCategoryTitle())
                            .parent(root)
                            .build());
        } else {
            cDao.save(Category.builder()
                            .categoryTitle(vo.getCategoryTitle())
                            .parent(vo.getParent())
                            .build());
        }

    }

    public void createPostByCategory(Category category) {
        if(category == null || category.getCategoryId() != 1) {
            Post vo = Post.builder()
                    .postTitle("임시")
                    .content("임시")
                    .category(category)
                    .build();
            pDao.save(vo);
        }

    }
}
