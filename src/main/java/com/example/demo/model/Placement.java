package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "placement")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Placement {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private int id;

//    @Column(unique = true)
    private String studentFullName;

    @ManyToOne
    @JoinColumn(name="departmentId", nullable=false)
    @JsonIgnore
    public Department department;
    private Double salary;
    private LocalDate placementDate;
    private Long phoneNumber;
    private String email;
    private  String companyName;
    private String designation;
    @Lob
    private byte[] photo;
}
