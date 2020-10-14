package com.putmeapp.restful.user;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;

    @Email(message = "* Please Enter Valid Email Address")
    @NotEmpty(message = " * Please Provide Email Address")
    private String email;

    @NotEmpty(message = "* Please Enter Password")
    @Size(min = 10, max = 200, message = "Password must be between 10 and 200 characters")
    private String password;
    private Date createdAt;
    private Date updatedAt;
}
