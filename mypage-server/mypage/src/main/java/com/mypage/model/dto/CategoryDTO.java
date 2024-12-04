package com.mypage.model.dto;

import com.mypage.model.vo.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private int categoryId;
    private String categoryTitle;
    private int parentId;

    private List<CategoryDTO> children;

    public CategoryDTO(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryTitle = category.getCategoryTitle();
        this.parentId = category.getParent().getCategoryId();

        if (category.getChildren() != null) {
            this.children = category.getChildren().stream()
                    .map(CategoryDTO::new)
                    .collect(Collectors.toList());
        }
    }
}
