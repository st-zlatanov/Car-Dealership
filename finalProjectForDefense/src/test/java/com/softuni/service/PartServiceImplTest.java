package com.softuni.service;

import com.softuni.base.TestBase;
import com.softuni.model.entity.Offer;
import com.softuni.model.entity.Part;
import com.softuni.model.entity.User;
import com.softuni.model.service.CategoryServiceModel;
import com.softuni.model.service.PartServiceModel;
import com.softuni.model.service.VehicleServiceModel;
import com.softuni.model.view.PartViewModel;
import com.softuni.repository.PartRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class PartServiceImplTest extends TestBase{
    @MockBean
    PartRepository partRepository;

    @Autowired
    PartService partService;

    @Test
    void findAllParts_shouldReturnCorrect() {
        Part part = new Part();
       part.setName("engine");
       part.setCarModel("audi a3");
       part.setImgUrl("http://res.cloudinary.com/st-zlatanov/image/upload/v1596969986/flcsmgijm7fzxgwficqn.jpg");
       part.setCondition("new");
       part.setDescription("asdfgg");
       part.setPrice(BigDecimal.ONE);

        List<Part> parts = new ArrayList<>(List.of(part));

        Mockito.when(partRepository.findAll()).thenReturn(parts);

        List<PartViewModel> returnedParts = partService.findAllParts();

        assertEquals(parts.size(), returnedParts.size());
    }
    @Test
    public void createPart_WhenCategoryIsNull_shouldThrowEx() {
        PartServiceModel model = new PartServiceModel();
        model.setCarModel("Audi a3");
        model.setCondition("used");
        model.setName("engine");
        model.setPrice(BigDecimal.ONE);
        model.setDescription("asdfggh");
        model.setCategory(null);

        assertThrows(Exception.class,
                () -> partService.addPart(model));
    }

//    @Test()
//    public void delete_whenExist_shouldDelete() {
//        String id = "1";
//        Part part = new Part();
//        part.setId(id);
//        Mockito.when(this.partRepository.findById(id))
//                .thenReturn(Optional.of(part))
//                .thenThrow(Exception.class);
//        this.partService.delete(id);
//        assertThrows(Exception.class,
//                () -> this.partService.delete(id));
//    }
}