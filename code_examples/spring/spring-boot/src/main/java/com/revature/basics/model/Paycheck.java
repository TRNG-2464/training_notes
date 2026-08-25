package com.revature.basics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "employeepaychecks", schema = "examples")
@Getter
@Setter
@ToString
@NoArgsConstructor  // JPA requires a no-args constructor on every entity
@AllArgsConstructor // convenient for creating fully-populated instances
public class Paycheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paycheckid")
    private Integer paycheckId;

    @Column(name = "empid")
    private Integer empId;

    @Column(name = "payfromdate")
    private LocalDate payFromDate;

    @Column(name = "paytodate")
    private LocalDate payToDate;

    @Column(name = "hoursworked")
    private BigDecimal hoursWorked;

    @Column(name = "grosspay")
    private BigDecimal grossPay;

    @Column(name = "totaldeductions")
    private BigDecimal totalDeductions;

    @Column(name = "netpay")
    private BigDecimal netPay;
}
