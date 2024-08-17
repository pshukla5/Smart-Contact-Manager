package com.scm.scm2_0.Forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 20, message = "Name must be between 2 to 20 characters")
    private String name;
    // @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email")
    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 to 20 characters")
    private String password;
    @NotBlank
    private String about;
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phoneNumber;
}
