package com.mobilar.filter.service.impl;

import com.mobilar.filter.entity.Technician;
import com.mobilar.filter.repository.TechnicianRepository;
import com.mobilar.filter.repository.specification.FilterCriteria;
import com.mobilar.filter.repository.specification.TechnicianSpecification;
import com.mobilar.filter.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TechnicianServiceImpl implements TechnicianService {


    @Autowired
    TechnicianRepository technicianRepo;

    @Override
    public List<Technician> getListOfTechniciansWithFilter(List<FilterCriteria> filters) {
        Specification result = null;
        if(filters!=null)
        for(FilterCriteria filterCriteria:filters){
            if(result==null) result=Specification.where(new TechnicianSpecification(filterCriteria));
            else result=result.and(new TechnicianSpecification(filterCriteria));
        }
        return technicianRepo.findAll(result);
    }
}
