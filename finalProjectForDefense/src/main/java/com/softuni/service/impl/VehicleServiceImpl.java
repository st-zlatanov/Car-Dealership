package com.softuni.service.impl;

import com.softuni.model.entity.Vehicle;
import com.softuni.model.service.VehicleServiceModel;
import com.softuni.repository.VehicleRepository;
import com.softuni.service.CategoryService;
import com.softuni.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void addVehicle(VehicleServiceModel vehicleServiceModel) {
        Vehicle vehicle = this.modelMapper.map(vehicleServiceModel, Vehicle.class);
        vehicle.setCategory(this.categoryService.find(vehicleServiceModel.getCategory().getName()));
        this.vehicleRepository.saveAndFlush(vehicle);
    }
}
