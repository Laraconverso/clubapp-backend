package com.APIclubApp.clubApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employees")

public class Employee extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_number")
    private Long employeeNumber;


}
