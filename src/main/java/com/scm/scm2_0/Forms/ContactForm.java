package com.scm.scm2_0.Forms;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactForm {

    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String description;
    private Boolean favorite;
    private String websiteLink;
    private String linkedInLink;
    private MultipartFile profileImage;
}
