package com.mobilar.filter.service;

import com.mobilar.filter.entity.Technician;
import com.mobilar.filter.repository.TechnicianRepository;
import com.mobilar.filter.repository.specification.FilterCriteria;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
public class TechnicianServiceTest {
    @Autowired
    private TechnicianRepository technicianRepo;

    @Autowired
    private TechnicianService technicianService;

    Technician technician_1,technician_2,technician_3;
    @BeforeEach
    public void init() {
        technician_1 = new Technician();
        technician_1.setFirstName("Mahmoud");
        technician_1.setLastName("Mohamed");
        technician_1.setEmail("mahmoud@bmw.com");
        technician_1.setAge(25);
        technicianRepo.save(technician_1);

        technician_2 = new Technician();
        technician_2.setFirstName("Karim");
        technician_2.setLastName("Ahmed");
        technician_2.setEmail("karim@bmw.com");
        technician_2.setAge(18);
        technicianRepo.save(technician_2);

        technician_3 = new Technician();
        technician_3.setFirstName("Tamer");
        technician_3.setLastName("Nouh");
        technician_3.setEmail("Tamer@bmw.com");
        technician_3.setAge(30);
        technicianRepo.save(technician_3);
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        List<Technician> results =technicianService.getListOfTechniciansWithFilter(null);
        assertThat("case1",results.contains(technician_1)&&results.contains(technician_2)&&results.contains(technician_3));

        results =technicianService.getListOfTechniciansWithFilter(Arrays.asList(
                new FilterCriteria("firstName","like","ahmou")
        ));
        assertThat("case2",results.contains(technician_1)&&!results.contains(technician_2)&&!results.contains(technician_3));

        results =technicianService.getListOfTechniciansWithFilter(Arrays.asList(
                new FilterCriteria("age",">",18),
                new FilterCriteria("age","<",30)
        ));
        assertThat("case3",results.contains(technician_1)&&!results.contains(technician_2)&&!results.contains(technician_3));

        results =technicianService.getListOfTechniciansWithFilter(Arrays.asList(
                new FilterCriteria("age",">=",18),
                new FilterCriteria("age","<=",30)
        ));
        assertThat("case4",results.contains(technician_1)&&results.contains(technician_2)&&results.contains(technician_3));

        results =technicianService.getListOfTechniciansWithFilter(Arrays.asList(
                new FilterCriteria("email","=","mahmoud@bmw.com")
        ));
        assertThat("case5",results.contains(technician_1)&&!results.contains(technician_2)&&!results.contains(technician_3));

        results =technicianService.getListOfTechniciansWithFilter(Arrays.asList(
                new FilterCriteria("email","!=","mahmoud@bmw.com")
        ));
        assertThat("case6",!results.contains(technician_1)&&results.contains(technician_2)&&results.contains(technician_3));

    }

    @AfterEach
    public void end() {
        technicianRepo.delete(technician_1);
        technicianRepo.delete(technician_2);
        technicianRepo.delete(technician_3);

    }

}
