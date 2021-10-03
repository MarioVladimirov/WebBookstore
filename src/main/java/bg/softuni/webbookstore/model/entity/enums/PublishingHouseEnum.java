package bg.softuni.webbookstore.model.entity.enums;

public enum PublishingHouseEnum {
    ПРОСВЕТА,
    СИЕЛА,
    ИЗТОК_ЗАПАД,
    КОЛИБРИ,
    БАРД,
    ЗАХАРИЙ_СТОЯНОВ,
    АБАГАР_ХОЛДИНГ;

    @Override
    public String toString() {
        String capitalized = name().charAt(0) + name().substring(1).toLowerCase();
        return capitalized.replaceAll("_", " ");
    }
}
