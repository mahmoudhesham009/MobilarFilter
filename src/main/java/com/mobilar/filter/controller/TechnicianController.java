package com.mobilar.filter.controller;

import com.mobilar.filter.entity.Technician;
import com.mobilar.filter.repository.specification.FilterCriteria;
import com.mobilar.filter.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technician")
public class TechnicianController {

    @Autowired
    TechnicianService technicianService;

    @PostMapping()
    ResponseEntity<List<Technician>> getTechniciansByFilter(@RequestBody List<FilterCriteria> filters){
        try {
            return ResponseEntity.ok().body(technicianService.getListOfTechniciansWithFilter(filters));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(technicianService.getListOfTechniciansWithFilter(filters));
        }
    }

}
