package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.entity.AuthorEntity;
import bg.softuni.webbookstore.model.service.AuthorAddServiceModel;
import bg.softuni.webbookstore.model.view.AuthorViewModel;
import bg.softuni.webbookstore.repository.AuthorRepository;
import bg.softuni.webbookstore.service.AuthorService;
import bg.softuni.webbookstore.service.BookService;
import bg.softuni.webbookstore.service.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> findAllAuthorsNames() {
        return authorRepository.findAllAuthorsNames();
    }

    @Override
    public Long add(AuthorAddServiceModel authorAddServiceModel) throws IOException {

        MultipartFile img = authorAddServiceModel.getImage();

        AuthorEntity authorEntity = modelMapper
                .map(authorAddServiceModel, AuthorEntity.class)
                .setImageUrl(
                        !"".equals(img.getOriginalFilename())
                                ? cloudinaryService.uploadImage(img)
                                : "https://res.cloudinary.com/nzlateva/image/upload/v1635173921/web-bookstore-app/authors-pics/default-author-pic_rc5wzc.png"
                );

        authorEntity = authorRepository.save(authorEntity);

        return authorEntity.getId();
    }

    @Override
    public AuthorViewModel findById(Long id) {
        AuthorEntity authorEntity = authorRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        return modelMapper
                .map(authorEntity, AuthorViewModel.class);
    }
}
