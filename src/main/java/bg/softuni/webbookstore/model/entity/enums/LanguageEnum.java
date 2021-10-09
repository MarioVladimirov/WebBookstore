package bg.softuni.webbookstore.model.entity.enums;

public enum LanguageEnum {
    BULGARIAN,
    ENGLISH,
    GERMAN,
    RUSSIAN;

    @Override
    public String toString() {
        String capitalized = name().charAt(0) + name().substring(1).toLowerCase();
        return capitalized.replaceAll("_", " ");
    }
}
