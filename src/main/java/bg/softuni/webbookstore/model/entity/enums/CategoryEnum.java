package bg.softuni.webbookstore.model.entity.enums;

public enum CategoryEnum {
    ФЕНТЪЗИ,
    ИСТОРИЯ,
    ИЗКУСТВО,
    ХОРЪР,
    ПОЕЗИЯ,
    ХУДОЖЕСТВЕНА_ЛИТЕРАТУРА,
    КРИМИНАЛНИ_И_ТРИЛЪРИ,
    РОМАНТИЧНИ,
    НАУЧНА_ФАНТАТИКА,
    НАУКА,
    КЛАСИЧЕСКИ_РОМАНИ,
    ДЕТСКА_ЛИТЕРАТУРА;

    @Override
    public String toString() {
        String capitalized = name().charAt(0) + name().substring(1).toLowerCase();
        return capitalized.replaceAll("_", " ");
    }
}
