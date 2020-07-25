package com.softuni.init;

import com.softuni.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.softuni.service.CategoryService;


@Component
public class DataInit implements CommandLineRunner {
    private final CategoryService categoryService;
    private final RoleService roleService;

    public DataInit(CategoryService categoryService, RoleService roleService) {
        this.categoryService = categoryService;
        this.roleService = roleService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initCategories();
        this.roleService.seedRolesInDb();
    }
}
