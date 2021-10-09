package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.repository.PublishingHouseRepository;
import bg.softuni.webbookstore.service.PublishingHouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishingHouseServiceImpl implements PublishingHouseService {

    private final PublishingHouseRepository publishingHouseRepository;

    public PublishingHouseServiceImpl(PublishingHouseRepository publishingHouseRepository) {
        this.publishingHouseRepository = publishingHouseRepository;
    }

    @Override
    public List<String> findAllPublishingHouseNames() {
        return publishingHouseRepository
                .findAllPublishingHouseNames();
    }
}
