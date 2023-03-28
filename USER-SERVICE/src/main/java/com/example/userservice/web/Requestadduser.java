package com.example.userservice.web;

import com.example.userservice.entities.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Requestadduser{
    public String userName;
    public String emailId;
    public String password;
    public String firstname;
    public String lastName;
    public String role;
}
