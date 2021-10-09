package bg.softuni.webbookstore.model.entity.enums;

public enum CategoryEnum {
    FICTION,
    CRIME_AND_THRILLER,
    ROMANCE,
    FANTASY,
    SCIENCE_FICTION,
    SCIENCE,
    CLASSICS,
    CHILDREN,
    HISTORY,
    ART,
    HORROR,
    POETRY;

    @Override
    public String toString() {
        String capitalized = name().charAt(0) + name().substring(1).toLowerCase();
        return capitalized.replaceAll("_", " ");
    }
}
