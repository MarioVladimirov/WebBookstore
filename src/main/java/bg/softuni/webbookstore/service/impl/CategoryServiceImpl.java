package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.CategoryEntity;
import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;
import bg.softuni.webbookstore.repository.CategoryRepository;
import bg.softuni.webbookstore.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity findByCategoryName(CategoryEnum category) {
        return categoryRepository
                .findByCategory(category)
                .orElseThrow(() -> new IllegalArgumentException("Category " + category + " not found"));
    }

    @Override
    public void seedCategories() {
        Arrays.stream(CategoryEnum.values())
                .map(categoryEnum -> new CategoryEntity().setCategory(categoryEnum))
                .forEach(categoryRepository::save);
    }
}
