package com.example.demo.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
//import org.apache.naming.java.j
import java.util.Set;


@Entity
@Table(name="Department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    public Department(String name, String code, String color){
        this.name = name;
        this.code = code;
        this.color = color;
    };
@Id
@GeneratedValue()
int id;
@Column(unique = true)
private String name;
private String color;
private String code;

@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinColumn(name = "hodId", referencedColumnName = "id")
@JsonIgnore
public User hod;

@OneToMany(targetEntity = User.class, cascade = CascadeType.ALL)
@JoinColumn(name = "departmentId", referencedColumnName = "id")
//@JsonIgnore
public Set<User> departmentMembers;
}
