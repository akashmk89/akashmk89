package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    private int id;
    private String name;
//    @ManyToMany(fetch = FetchType.LAZY, targetEntity = User.class, mappedBy = "roles", cascade = CascadeType.ALL)
//    private Set<User> users;
}
