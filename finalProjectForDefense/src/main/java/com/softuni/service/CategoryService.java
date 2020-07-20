package com.softuni.service;

import com.softuni.model.entity.Category;
import com.softuni.model.entity.CategoryName;

public interface CategoryService {
    void initCategories();

    Category find(CategoryName name);
}
