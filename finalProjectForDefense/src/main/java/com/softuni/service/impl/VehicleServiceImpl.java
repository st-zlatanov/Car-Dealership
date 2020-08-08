package com.softuni.service.impl;

import com.softuni.error.VehicleNotFoundException;
import com.softuni.model.entity.Category;
import com.softuni.model.entity.CategoryName;
import com.softuni.model.entity.User;
import com.softuni.model.entity.Vehicle;
import com.softuni.model.service.VehicleServiceModel;
import com.softuni.model.view.VehicleViewModel;
import com.softuni.repository.VehicleRepository;
import com.softuni.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final AuthenticatedUserService authenticatedUserService;
    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper, CategoryService categoryService, AuthenticatedUserService authenticatedUserService, UserService userService, CloudinaryService cloudinaryService) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.authenticatedUserService = authenticatedUserService;
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void addVehicle(VehicleServiceModel vehicleServiceModel) throws IOException {
        Vehicle vehicle = this.modelMapper.map(vehicleServiceModel, Vehicle.class);

        String username = authenticatedUserService.getUsername();
        User user = this.modelMapper.map(this.userService.findByUsername(username), User.class);

        vehicle.setCategory(this.categoryService.find(vehicleServiceModel.getCategory().getName()));
        vehicle.setOwner(user);
        vehicle.setImgUrl(cloudinaryService.uploadImage(vehicleServiceModel.getImage()));
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

        return this.vehicleRepository
                .findById(id)
                .map(v -> this.modelMapper.map(v, VehicleViewModel.class))
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with the given id was not found!"));
    }

    @Override
    public List<VehicleViewModel> findAllVehiclesByCategory(CategoryName category) {
        Category category1 = this.categoryService.find(category);
        return this.vehicleRepository.findAll().stream().filter(v->v.getCategory().getName().equals(category1.getName()))
                .map(vehicle -> {
                    VehicleViewModel vehicleViewModel = this.modelMapper.map(vehicle, VehicleViewModel.class);


                    return vehicleViewModel;
                })
                .collect(Collectors.toList());
    }


}
