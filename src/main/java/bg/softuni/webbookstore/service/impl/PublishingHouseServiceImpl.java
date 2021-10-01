package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.PublishingHouseEntity;
import bg.softuni.webbookstore.repository.PublishingHouseRepository;
import bg.softuni.webbookstore.service.PublishingHouseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishingHouseServiceImpl implements PublishingHouseService {

    private final PublishingHouseRepository publishingHouseRepository;
    private final ModelMapper modelMapper;

    public PublishingHouseServiceImpl(PublishingHouseRepository publishingHouseRepository, ModelMapper modelMapper) {
        this.publishingHouseRepository = publishingHouseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> findAllPublishingHousesNames() {
        return publishingHouseRepository
                .findAllPublishingHousesNames();
    }

    @Override
    public PublishingHouseEntity findByName(String name) {
        return publishingHouseRepository
                .findByName(name)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Publishing House with name %s not found",
                                name)
                ));
    }
}
