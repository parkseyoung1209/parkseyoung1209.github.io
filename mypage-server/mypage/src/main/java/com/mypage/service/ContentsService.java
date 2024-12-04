package com.mypage.service;

import com.mypage.model.dao.CategoryDAO;
import com.mypage.model.dao.PostDAO;
import com.mypage.model.dto.CategoryDTO;
import com.mypage.model.vo.Category;
import com.mypage.model.vo.Post;
import com.mypage.model.vo.QCategory;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentsService {

    @Autowired
    private CategoryDAO cDao;

    @Autowired
    private PostDAO pDao;

    @Autowired
    private JPAQueryFactory queryFactory;

    public List<Category> allCategory() {
       return cDao.findAll();
    }

    @Transactional
    public void createOrUpdateCategory(CategoryDTO categoryDTO) {
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

        Category parentCategory = categoryDTO.getParentId() == 0
                ? root
                : cDao.findById(categoryDTO.getParentId()).orElse(null);

        Category category;

        if(categoryDTO.getCategoryId() != 0) { // update 관련
            category = cDao.findById(categoryDTO.getCategoryId()).orElse(null);
            category.setCategoryTitle(categoryDTO.getCategoryTitle());

            if(!category.getParent().equals(parentCategory)) {
                category.setParent(parentCategory);
                if (!parentCategory.getChildren().contains(category)) {
                    parentCategory.getChildren().add(category);
                }
            }
        } else {
            category = Category.builder()
                    .categoryTitle(categoryDTO.getCategoryTitle())
                    .parent(parentCategory)
                    .build();
            if(parentCategory.getChildren() == null) parentCategory.setChildren(new ArrayList<>());
            parentCategory.getChildren().add(category);
        }
        cDao.save(category);
    }

    public void deleteCategory(int categoryId) {
        cDao.deleteById(categoryId);
    }

    public List<CategoryDTO> findByparentIdList (int parentId) {
        QCategory qCategory = QCategory.category;
        BooleanBuilder builder = new BooleanBuilder();

        if(parentId != 0) builder.and(qCategory.parent.categoryId.eq(parentId));

        List<Category> categories = queryFactory.selectFrom(qCategory)
                .where(builder)
                .fetch();

        return categories.stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
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
