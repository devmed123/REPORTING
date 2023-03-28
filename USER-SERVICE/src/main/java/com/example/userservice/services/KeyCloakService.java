package com.example.userservice.services;

import com.example.userservice.Credentials;
import com.example.userservice.config.KeycloakConfig;
import com.example.userservice.entities.UserDTO;
import com.example.userservice.web.Requestadduser;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;

import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class KeyCloakService {


    public Response addUser(Requestadduser userDTO){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        user.setEmailVerified(true);
        UsersResource instance = getInstance();
        Response response= instance.create(user);
        System.out.printf("Repsonse: %s %s  %s %n", response.getStatus(), response.getStatusInfo() );
        if(response.getStatus()==201){
            RoleRepresentation role= getClient().realm("reporting").roles().get(userDTO.getRole()).toRepresentation();
            List<RoleRepresentation> roles=new ArrayList<RoleRepresentation>();
            roles.add(role);
            String userId = CreatedResponseUtil.getCreatedId(response);
            UserResource userResource = getClient().realm("reporting").users().get(userId);
            userResource.roles().realmLevel()
                    .add(Arrays.asList(role));
            return response;
         }
        else{
            return response;
        }



    }

    public List<UserRepresentation> getUser(String userName){
        UsersResource usersResource = getInstance();
        List<UserRepresentation> user = usersResource.search(userName, true);
        return user;

    }

    public void updateUser(String userId, UserDTO userDTO){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = getInstance();
        usersResource.get(userId).update(user);
    }
    public void deleteUser(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .remove();
    }


    public void sendVerificationLink(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .sendVerifyEmail();
    }

    public void sendResetPassword(String userId){
        UsersResource usersResource = getInstance();

        usersResource.get(userId)
                .executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
    }

    public UsersResource getInstance(){
        return KeycloakConfig.getInstance().realm("reporting").users();
    }

    public Keycloak getClient(){
        return KeycloakConfig.getInstance();
    }
    public List<String> getAllRoles(){
        List<String> availableRoles = getClient()
                .realm("reporting")
                .roles()
                .list()
                .stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());
        return availableRoles;
    }

}