package com.softuni.repository;

import com.softuni.model.entity.Vehicle;
import com.softuni.model.view.VehicleViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Vehicle findVehicleById(String id);

}
