package bg.softuni.webbookstore.model.entity;

import bg.softuni.webbookstore.model.entity.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryEnum category;

    public CategoryEnum getCategory() {
        return category;
    }

    public CategoryEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
