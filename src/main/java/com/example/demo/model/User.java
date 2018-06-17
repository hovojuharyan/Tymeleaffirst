package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    private String password;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column(name = "prof_type")
    @Enumerated(EnumType.STRING)
    private UserProfType userProfType;
    @Column(name = "pic_url")
    private String picUrl;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_project",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;
}
