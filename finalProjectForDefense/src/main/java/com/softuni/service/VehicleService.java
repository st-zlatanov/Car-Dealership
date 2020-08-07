package com.softuni.service;

import com.softuni.model.entity.CategoryName;
import com.softuni.model.service.VehicleServiceModel;
import com.softuni.model.view.VehicleViewModel;

import java.util.List;

public interface VehicleService {
    void addVehicle(VehicleServiceModel vehicleServiceModel);
    List<VehicleViewModel> findAllVehicles();
    VehicleViewModel findVehicleById(String id);
    List<VehicleViewModel> findAllVehiclesByCategory(CategoryName category);


}
