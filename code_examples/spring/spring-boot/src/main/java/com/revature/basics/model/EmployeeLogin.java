package com.revature.basics.model;

import com.revature.basics.util.Role;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "employeelogin", schema = "examples")
@Getter
@Setter
@ToString
@NoArgsConstructor  // JPA requires a no-args constructor on every entity
@AllArgsConstructor // convenient for creating fully-populated instances
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

    /*
     * The @Enumerated(EnumType.STRING) tells Hibernate to store/read
     * this field as the enum constant's NAME (e.g. "ROLE_ADMIN").
     *
     * Note: In the Database, we stored this as a VARCHAR - so we need
     * to read the Enum's String value. Without this 'EnumType.STRING'
     * Hibernate would read the Enum's ORDINAL (numeric position) value
     * instead (e.g. 0, 1, 2...)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "enabled")
    private Boolean enabled;
}
