package com.userservice.Exception;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
public class UserAlreadyExistsException extends RuntimeException{

    private String resourcename;
    private String fieldname;
    private String email;

    public UserAlreadyExistsException(String resourcename, String fieldname, String email){
        super(String.format("%s already exists with %s : %s",resourcename,fieldname,email));
        this.resourcename=resourcename;
        this.fieldname=fieldname;
        this.email=email;
    }
}
