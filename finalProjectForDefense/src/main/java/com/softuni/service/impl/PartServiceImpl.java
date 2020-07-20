package com.softuni.service.impl;

import com.softuni.model.entity.Part;
import com.softuni.model.entity.Vehicle;
import com.softuni.model.service.PartServiceModel;
import com.softuni.model.view.PartViewModel;
import com.softuni.model.view.VehicleViewModel;
import com.softuni.repository.PartRepository;
import com.softuni.service.CategoryService;
import com.softuni.service.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<PartViewModel> findAllParts() {
        return this.partRepository.findAll().stream()
                .map(part -> {
                    PartViewModel partViewModel = this.modelMapper.map(part, PartViewModel.class);

                    return partViewModel;
                })
                .collect(Collectors.toList());
    }
}
