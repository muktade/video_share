package com.example.myyoutube.entity;

import com.example.myyoutube.baseentity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "name")
    private String  name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
