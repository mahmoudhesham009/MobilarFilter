package com.mobilar.filter.service;

import com.mobilar.filter.entity.Technician;
import com.mobilar.filter.repository.specification.FilterCriteria;

import java.util.List;

public interface TechnicianService {
    List<Technician> getListOfTechniciansWithFilter(List<FilterCriteria> filters);
}
