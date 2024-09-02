package com.scm.scm2_0.Services.Implementations;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.scm2_0.Helper.AppConstants;
import com.scm.scm2_0.Services.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }



    @Override
    public String uploadImage(MultipartFile contactImage, String cloudinaryImagePublicId) {
        
        // writting code which is uploading image and returning URL
        try {

            byte[] data = new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(data);

            cloudinary.uploader().upload(data, ObjectUtils.asMap("public_id", cloudinaryImagePublicId));

            return this.getUrlFromPublicId(cloudinaryImagePublicId);
            
        }
        catch (IOException e){

            e.printStackTrace();
            return null;
        }
    }



    @Override
    public String getUrlFromPublicId(String cloudinaryImagePublicId) {

        return cloudinary
                .url()
                .transformation(

                    new Transformation<>()
                        .width(AppConstants.CONTACT_IMAGE_WIDTH)
                        .height(AppConstants.CONTACT_IMAGE_HEIGHT)
                        .crop(AppConstants.CONTACT_IMAGE_CROP)
                )
                .generate(cloudinaryImagePublicId);
    }

}
