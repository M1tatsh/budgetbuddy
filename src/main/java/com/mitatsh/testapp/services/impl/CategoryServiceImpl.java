package com.mitatsh.testapp.services.impl;

import com.mitatsh.testapp.domain.entities.Category;
import com.mitatsh.testapp.repositories.CategoryRepository;
import com.mitatsh.testapp.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Lists all categories with their income counts.
     */
    @Override
    public List<Category> listCategories(){
        return categoryRepository.findAllWithIncomeCount();
    }

    /**
     * Create a new category with a unique name.
     * @param category Category to create.
     */
    @Override
    @Transactional
    public Category createCategory(Category category) {
        if(categoryRepository.existsByNameIgnoreCase(category.getName())){
            throw new IllegalArgumentException("Category already exists with name: " + category.getName());
        }
        return categoryRepository.save(category);
    }

    /**
     * Delete an existing category.
     * @param id UUID of the category to delete
     */
    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            //Implement check for categories that are assigned to income or expense objects already and throw
            //exception if so.
            categoryRepository.deleteById(id);
        }
    }
}
