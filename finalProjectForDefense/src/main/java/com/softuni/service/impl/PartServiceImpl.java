package com.softuni.service.impl;

import com.softuni.model.entity.Part;
import com.softuni.model.entity.Vehicle;
import com.softuni.model.service.PartServiceModel;
import com.softuni.repository.PartRepository;
import com.softuni.service.CategoryService;
import com.softuni.service.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void addPart(PartServiceModel partServiceModel) {
        Part part = this.modelMapper.map(partServiceModel, Part.class);
        part.setCategory(this.categoryService.find(partServiceModel.getCategory().getName()));
        this.partRepository.saveAndFlush(part);
    }
}
