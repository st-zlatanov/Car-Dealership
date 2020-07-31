package com.softuni.service.impl;

import com.softuni.model.entity.Vehicle;
import com.softuni.model.service.VehicleServiceModel;
import com.softuni.model.view.VehicleViewModel;
import com.softuni.repository.VehicleRepository;
import com.softuni.service.CategoryService;
import com.softuni.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
        vehicle.setComments(new HashSet<>());
        vehicle.setLikes(0);
        this.vehicleRepository.saveAndFlush(vehicle);
    }

    @Override
    public List<VehicleViewModel> findAllVehicles() {
        return this.vehicleRepository.findAll().stream()
                .map(vehicle -> {
                    VehicleViewModel vehicleViewModel = this.modelMapper.map(vehicle, VehicleViewModel.class);

                    return vehicleViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VehicleViewModel findVehicleById(String id) {

        return this.modelMapper.map(vehicleRepository.findVehicleById(id), VehicleViewModel.class);
    }


}
