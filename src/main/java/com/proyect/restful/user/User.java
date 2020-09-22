package com.proyect.restful.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email(message = "* Please Enter Valid Email Address")
    @NotEmpty(message = " * Please Provide Email Address")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password_hash")
    @NotEmpty(message = "* Please Enter Password")
    @Size(min = 10, max = 200, message = "Password must be between 10 and 200 characters")
    private String password;

}