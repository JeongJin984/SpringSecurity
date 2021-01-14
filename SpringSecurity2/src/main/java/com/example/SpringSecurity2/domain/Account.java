package com.example.SpringSecurity2.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Account implements Serializable {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;
    private String age;
    private String role;

}
