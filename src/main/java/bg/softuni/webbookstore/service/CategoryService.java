package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.entity.CategoryEntity;
import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;

public interface CategoryService {

    CategoryEntity findByCategoryName(CategoryEnum category);

    void seedCategories();
}
