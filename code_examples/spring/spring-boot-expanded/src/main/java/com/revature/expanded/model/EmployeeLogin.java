package com.revature.expanded.model;

import com.revature.expanded.util.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employeelogin")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loginid")
    private Integer loginId;

    @Column(name = "empid")
    private Integer empId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "enabled")
    private Boolean enabled;
}
