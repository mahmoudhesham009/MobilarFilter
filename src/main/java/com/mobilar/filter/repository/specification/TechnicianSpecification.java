package com.mobilar.filter.repository.specification;

import com.mobilar.filter.entity.Technician;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@AllArgsConstructor
public class TechnicianSpecification implements Specification<Technician> {

    FilterCriteria filter;


    @Override
    public Predicate toPredicate(Root<Technician> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(filter.getOperation().equals("=")){
            return criteriaBuilder.equal(
                    root.get(filter.getKey()),  filter.getValue());
        }else if(filter.getOperation().equals("!=")){
            return criteriaBuilder.notEqual(
                    root.get(filter.getKey()), filter.getValue() );
        }else if(filter.getOperation().equals("like")){
            if(filter.getValue()instanceof String)
                return criteriaBuilder.like(
                        root.get(filter.getKey()), "%" + filter.getValue() + "%");
        }else if(filter.getOperation().equals(">")){
            if(filter.getValue()instanceof Integer)
                return criteriaBuilder.greaterThan(
                        root.get(filter.getKey()), (Integer) filter.getValue());
        }else if(filter.getOperation().equals(">=")){
            if(filter.getValue()instanceof Integer)
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(filter.getKey()),(Integer) filter.getValue());
        }else if(filter.getOperation().equals("<")){
            if(filter.getValue()instanceof Integer)
                return criteriaBuilder.lessThan(
                        root.get(filter.getKey()), (Integer)filter.getValue());
        }else if(filter.getOperation().equals("<=")){
            if(filter.getValue()instanceof Integer)
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(filter.getKey()), (Integer)filter.getValue());
        }
        return null;
    }
}
