package guru.springframework.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {

    }
}