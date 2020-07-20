package com.softuni.service;

import com.softuni.model.service.PartServiceModel;
import com.softuni.model.view.PartViewModel;
import java.util.List;


public interface PartService {
    void addPart(PartServiceModel partServiceModel);
    List<PartViewModel> findAllParts();
}
