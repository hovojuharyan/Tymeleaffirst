package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project")
@Entity
public class Project {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private String title;
    @Column
    private String description;
    @Column(name = "time_stamp")
    private String timeStamp;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @ManyToMany(mappedBy = "projects")
    private List<User> users;
}
