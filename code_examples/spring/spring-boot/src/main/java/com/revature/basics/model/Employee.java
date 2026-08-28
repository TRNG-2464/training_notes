package com.revature.basics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

// The @Entity annotation tells Spring Data JPA
// That this class represents a record in our database
// i.e. This model matches the expected table schema (columns)
@Entity
@Table(name = "employees", schema="examples")
@Getter
@Setter
@NoArgsConstructor  // JPA requires a no-args constructor on every entity
@AllArgsConstructor // convenient for creating fully-populated instances
public class Employee {

    @Id
    @Column(name = "emp_id")    // This @Column annotation is OPTIONAL if your Entity
    private Integer empId;      // class uses the exact same name as your DB table schema

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_title")
    private String empTitle;

    @Column(name = "emp_salary")
    private BigDecimal empSalary;
}
