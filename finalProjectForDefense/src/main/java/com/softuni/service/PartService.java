package com.softuni.service;

import com.softuni.model.service.PartServiceModel;
import com.softuni.model.view.PartViewModel;

import java.io.IOException;
import java.util.List;


public interface PartService {
    void addPart(PartServiceModel partServiceModel) throws IOException;
    List<PartViewModel> findAllParts();

    void delete(String id);
}
