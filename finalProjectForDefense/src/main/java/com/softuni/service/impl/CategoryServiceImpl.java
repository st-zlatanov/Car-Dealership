package com.softuni.service.impl;

import com.softuni.model.entity.Category;
import com.softuni.model.entity.CategoryName;
import com.softuni.repository.CategoryRepository;
import com.softuni.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public void initCategories() {
        if(this.categoryRepository.count()==0){
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        this.categoryRepository.save(new Category(categoryName,
                                String.format("Description for %s",
                                        categoryName.name())));
                    });
        }
    }

    @Override
    public Category find(CategoryName name) {
        return this.categoryRepository
                .findByName(name).orElse(null);
    }
}
