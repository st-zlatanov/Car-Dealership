package com.softuni.service.impl;

import com.softuni.error.PartNotFoundException;
import com.softuni.error.VehicleNotFoundException;
import com.softuni.model.entity.Part;
import com.softuni.model.service.PartServiceModel;
import com.softuni.model.view.PartViewModel;
import com.softuni.repository.PartRepository;
import com.softuni.service.CategoryService;
import com.softuni.service.CloudinaryService;
import com.softuni.service.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final CloudinaryService cloudinaryService;

    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, CategoryService categoryService, CloudinaryService cloudinaryService) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void addPart(PartServiceModel partServiceModel) throws IOException {
        Part part = this.modelMapper.map(partServiceModel, Part.class);
        part.setCategory(this.categoryService.find(partServiceModel.getCategory().getName()));
        part.setImgUrl(cloudinaryService.uploadImage(partServiceModel.getImage()));
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

    @Override
    public void delete(String id) {
        try {
            this.partRepository.deleteById(id);
        }catch (Exception ex){
            throw new PartNotFoundException();
        }
    }
}
