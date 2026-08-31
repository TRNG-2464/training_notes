package com.revature.expanded.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_title")
    private String empTitle;

    @Column(name = "emp_salary")
    private BigDecimal empSalary;
}
