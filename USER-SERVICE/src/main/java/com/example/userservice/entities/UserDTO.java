package com.example.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserDTO implements Serializable {
    public String userName;
    public String emailId;
    public String password;
    public String firstname;
    public String lastName;
}