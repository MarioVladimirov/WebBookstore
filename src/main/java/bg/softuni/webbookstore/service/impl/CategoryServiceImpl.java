package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.repository.CategoryRepository;
import bg.softuni.webbookstore.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

}
