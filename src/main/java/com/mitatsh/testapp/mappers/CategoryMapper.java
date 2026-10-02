package com.mitatsh.testapp.mappers;

import com.mitatsh.testapp.domain.dtos.CategoryDto;
import com.mitatsh.testapp.domain.dtos.CreateCategoryRequest;
import com.mitatsh.testapp.domain.entities.Category;
import com.mitatsh.testapp.domain.entities.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target="incomeCount",source="income",qualifiedByName="calculateIncomeCount")
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculateIncomeCount")
    default long calculateIncomeCount(List<Income> income) {
        if(income == null){
            return 0;
        }
        return income.size();
    }
}
