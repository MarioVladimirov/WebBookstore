package bg.softuni.webbookstore.model.binding;

import bg.softuni.webbookstore.model.entity.AuthorEntity;
import bg.softuni.webbookstore.model.entity.CategoryEntity;
import bg.softuni.webbookstore.model.entity.PublishingHouseEntity;
import bg.softuni.webbookstore.model.entity.enums.LanguageEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public class BookAddBindingModel {

    private String isbn;
    private String title;
    private String description;
    private String imageUrl;
    private Integer pagesCount;
    private Integer copies;
    private Integer releaseYear;
    private BigDecimal price;

    @NotNull
    private LanguageEnum language;

    @NotNull
    private Set<CategoryEntity> categories;

    @NotNull
    private AuthorEntity author;

    @NotNull
    private PublishingHouseEntity publishingHouse;
}
