package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.cloudinary.CloudinaryImage;
import bg.softuni.webbookstore.service.CloudinaryService;
import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public CloudinaryImage uploadImage(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary
                    .uploader()
                    .upload(tempFile, Collections.emptyMap());

            String url = uploadResult.getOrDefault(URL,
                    "");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            return new CloudinaryImage()
                    .setUrl(url)
                    .setPublicId(publicId);

        } finally {
            tempFile.delete();
        }
    }

    @Override
    public boolean deleteImage(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Collections.emptyMap());
        } catch (IOException e) {
            return false;
        }
        return true;

//        try {
//            String publicId = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.lastIndexOf("."));
//            this.cloudinary.uploader().destroy(publicId, Collections.emptyMap());
//        } catch (IOException e) {
//            return false;
//        }
//        return true;
    }
}
