package bg.softuni.webbookstore.model.view;

public class PublishingHouseViewModel {

    private String name;
    private String description;
    private String imageUrl;


    public PublishingHouseViewModel() {
    }

    public String getName() {
        return name;
    }

    public PublishingHouseViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PublishingHouseViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PublishingHouseViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
