package com.example.springboothibernatelogin.form;

import lombok.Data;

@Data
public class PersonUpdateForm {
    private int id;
    private String userName;
    private String password;
    private String name;
    private int age;
    private int phone;
}
