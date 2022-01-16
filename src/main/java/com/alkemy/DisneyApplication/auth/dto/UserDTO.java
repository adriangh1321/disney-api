package com.alkemy.DisneyApplication.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    
    @NotNull(message = "Username must not be null")
    @Email(message = "Username must be an email")
    private String username;
    
    @NotBlank(message = "Password must have no spaces")
    @NotNull(message = "Password must not be null")
    @Size(min = 8,max = 16, message = "The password must be between 8 and 16 characters long")
    
    private String password;

}
