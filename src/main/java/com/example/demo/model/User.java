package com.example.demo.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
//@Builder
@NoArgsConstructor
@Table(name="User")
public class User {
@Id
@GeneratedValue()
int id;
private String name;
private String email;
private String phoneNum;
private String password;
private boolean enabled;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<Role>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="departmentId", nullable=false)
    @JsonIgnore
    public Department department;

}
