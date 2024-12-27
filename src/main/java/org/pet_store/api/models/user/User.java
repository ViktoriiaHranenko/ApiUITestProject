package org.pet_store.api.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String id;
    private String username;
    private String firsName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
