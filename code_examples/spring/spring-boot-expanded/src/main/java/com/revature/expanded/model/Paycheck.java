package com.revature.expanded.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employeepaychecks")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
