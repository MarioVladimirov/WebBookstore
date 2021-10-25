package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.PublishingHouseEntity;
import bg.softuni.webbookstore.model.view.PublishingHouseViewModel;
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
    public List<String> findAllPublishingHouseNames() {
        return publishingHouseRepository
                .findAllPublishingHouseNames();
    }

    @Override
    public PublishingHouseViewModel findById(Long id) {
        PublishingHouseEntity publishingHouse = publishingHouseRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Publishing House not found"));

        return modelMapper
                .map(publishingHouse, PublishingHouseViewModel.class);
    }
}
