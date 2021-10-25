package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.view.PublishingHouseViewModel;

import java.util.List;

public interface PublishingHouseService {

    List<String> findAllPublishingHouseNames();

    PublishingHouseViewModel findById(Long id);
}
