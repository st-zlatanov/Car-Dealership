package com.softuni.service;

import com.softuni.base.TestBase;
import com.softuni.error.VehicleNotFoundException;
import com.softuni.model.entity.Vehicle;
import com.softuni.model.service.VehicleServiceModel;

import com.softuni.model.view.VehicleViewModel;

import com.softuni.repository.VehicleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VehicleServiceImplTest extends TestBase {
    private List<Vehicle> vehicleList;


    @MockBean
    VehicleRepository vehicleRepository;

    @Autowired
    VehicleService vehicleService;

    @BeforeEach
    public void setupTest() {
        vehicleList = new ArrayList<>();
        when(vehicleRepository.findAll())
                .thenReturn(vehicleList);
    }

    @Test
    public void creatingVehicle_whenVehicleDoesntExist_ShouldCreate() {
        vehicleList.clear();

        Vehicle vehicle = new Vehicle();

        vehicleList.add(vehicle);

        List<VehicleViewModel> results = vehicleService.findAllVehicles();
        assertEquals(1, results.size());
    }
    @Test
    public void findAllVehicles_whenNoVehicles_shouldReturnEmpty() {
        vehicleList.clear();
        List<VehicleViewModel> allV = vehicleService.findAllVehicles();
        Assert.assertTrue(allV.isEmpty());
    }

    @Test
    void findAllVehicles_whenVehiclesArePresent_shouldReturnCorrect() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("audi");
        vehicle.setModel("a3");

        List<Vehicle> vehicles = new ArrayList<>(List.of(vehicle));

        when(vehicleRepository.findAll()).thenReturn(vehicles);

        List<VehicleViewModel> returnedVehicles = vehicleService.findAllVehicles();

        assertEquals(vehicles.size(), returnedVehicles.size());
    }


    @Test
    public void createVehicle_WhenUserIsNull_shouldThrowEx() {
        VehicleServiceModel model = new VehicleServiceModel();
        assertThrows(Exception.class,
                () -> vehicleService.addVehicle(model));
    }

    @Test
    public void findByid_whenNotFound_shouldThrowEx() {
        assertThrows(VehicleNotFoundException.class,
                () -> vehicleService.findVehicleById("id"));
    }
    @Test
    public void createVehicle_WhenModelIsNull_shouldThrowEx() {
        VehicleServiceModel model = null;
        assertThrows(Exception.class,
                () -> vehicleService.addVehicle(model));
    }
}