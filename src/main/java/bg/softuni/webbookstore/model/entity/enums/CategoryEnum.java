package bg.softuni.webbookstore.model.entity.enums;

public enum CategoryEnum {
    ХУДОЖЕСТВЕНА_ЛИТЕРАТУРА,
    КРИМИНАЛНИ_И_ТРИЛЪРИ,
    РОМАНТИЧНИ,
    ФЕНТЪЗИ,
    НАУЧНА_ФАНТАТИКА,
    НАУКА,
    КЛАСИЧЕСКИ_РОМАНИ,
    ДЕТСКА_ЛИТЕРАТУРА,
    ИСТОРИЯ,
    ИЗКУСТВО,
    ХОРЪР,
    ПОЕЗИЯ;

    @Override
    public String toString() {
        String capitalized = name().charAt(0) + name().substring(1).toLowerCase();
        return capitalized.replaceAll("_", " ");
    }
}
