package com.scm.scm2_0.Services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadImage(MultipartFile contactImage, String cloudinaryImagePublicId);

    String getUrlFromPublicId(String publicId);

    void deleteUsingPublicId(String cloudinaryImagePublicId);

}
