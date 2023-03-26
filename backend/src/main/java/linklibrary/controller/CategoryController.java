package linklibrary.controller;

import linklibrary.dto.CategoryDto;
import linklibrary.dto.ResponseData;
import linklibrary.security.auth.PrincipalDetails;
import linklibrary.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long savedCategoryId = categoryService.createCategory(categoryDto, principalDetails.getUser());
        return ResponseEntity.ok(new ResponseData("카테고리 생성 완료", savedCategoryId));
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<?> editCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable final Long categoryId) {
        Long editedCategoryId = categoryService.editCategory(categoryDto, categoryId);
        return ResponseEntity.ok(new ResponseData("카테고리 수정 완료", editedCategoryId));
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<?> editCategory(@PathVariable final Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new ResponseData("카테고리 삭제 완료", null));
    }

    @GetMapping("/categories")
    public ResponseEntity<?> findCategoryByUser(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUser().getId();
        List<CategoryDto> categoryDtoList = categoryService.findAll(userId);
        return ResponseEntity.ok(new ResponseData("카테고리 반환", categoryDtoList));
    }
}