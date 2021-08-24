package com.example.springboothibernatelogin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Person")
@Data
// builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name",length = 50, nullable = false)
    private String userName;

    @Column( length = 50, nullable = false)
    private String password;

    @Column( length = 50, nullable = false)
    private String name;

    @Column( nullable = false)
    private int age;

    @Column
    private int phone;

}
