package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.entity.PublishingHouseEntity;

import java.util.List;

public interface PublishingHouseService {

    List<String> findAllPublishingHousesNames();

    PublishingHouseEntity findByName(String name);

}
