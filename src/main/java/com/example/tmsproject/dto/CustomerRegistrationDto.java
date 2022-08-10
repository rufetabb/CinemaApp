package com.example.tmsproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CustomerRegistrationDto {
    @NotBlank(message ="Adı daxil edin")
//    @Length(min = 2,max = 50,message ="ad ən azı 2 simvol olmalidir")
    private String firstName;
    @NotBlank(message ="Sayadı daxil edin")
//    @Length(min = 2,max = 50,message ="soyad ən azı 2 simvol olmalidir")
    private String lastName;
    @NotBlank(message ="Emaili daxil edin")
    @Email(message = "Email doğru deyil")
    private String email;
    @NotBlank(message ="Şifrəni daxil edin")
    @Length(min = 8,message ="şifrə ən azı 8 simvol olmalidir")
    private String password;
    @NotBlank(message ="Şifrəni təkrar daxil edin")
    @Length(min = 8,message = "")
    private String rPassword;

    public CustomerRegistrationDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public CustomerRegistrationDto() {
    }

    public CustomerRegistrationDto(String firstName, String lastName, String email, String password, String rPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rPassword = rPassword;
    }

    public String getrPassword() {
        return rPassword;
    }

    public void setrPassword(String rPassword) {
        this.rPassword = rPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
