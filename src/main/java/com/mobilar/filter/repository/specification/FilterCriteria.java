package com.mobilar.filter.repository.specification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilterCriteria {
    String key,operation;
    Object value;
}
