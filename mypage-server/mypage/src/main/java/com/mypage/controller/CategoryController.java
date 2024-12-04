package com.mypage.controller;

import com.mypage.model.vo.Category;
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

    @PostMapping("private/addCategory")
    public ResponseEntity addCategory(@RequestBody Category vo, int parentId) {
        cService.createCategory(vo, parentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
