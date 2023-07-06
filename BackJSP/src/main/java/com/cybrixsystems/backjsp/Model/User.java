package com.cybrixsystems.backjsp.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String name;
    private String surname;
    private String dni;
    private String email;
    private String password;

    public User(String name,String surname,String dni,String email, String password){
        if(name != null && surname !=null && email != null && password != null
                    && dni != null && dni.length() == 8){
                this.name = name;
                this.surname = surname;
                this.dni = dni;
                this.email = email;
                this.password = password;
        }
    }
}
