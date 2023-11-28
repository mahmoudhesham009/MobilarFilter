package com.mobilar.filter.repository;

import com.mobilar.filter.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TechnicianRepository extends JpaRepository<Technician,Integer> , JpaSpecificationExecutor<Technician> {
}
