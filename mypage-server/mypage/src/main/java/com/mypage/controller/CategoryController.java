package com.mypage.controller;

import com.mypage.model.dto.CategoryDTO;
import com.mypage.service.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myblog/*")
@CrossOrigin(origins = {"*"}, maxAge=6000)
public class CategoryController {

    @Autowired
    private ContentsService cService;

    @PostMapping("private/category")
    public ResponseEntity addCategory(@RequestBody CategoryDTO vo) {
        cService.createOrUpdateCategory(vo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("private/category")
    public ResponseEntity updateCategory(@RequestBody CategoryDTO vo) {
        cService.createOrUpdateCategory(vo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("private/category")
    public ResponseEntity deleteCategory(@RequestParam(name = "categoryId") int categoryId) {
        cService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("category/parent")
    public ResponseEntity findParentCategory(@RequestParam(name = "parentId") int parentId) {
        return ResponseEntity.status(HttpStatus.OK).body(cService.findByparentIdList(parentId));
    }
}
