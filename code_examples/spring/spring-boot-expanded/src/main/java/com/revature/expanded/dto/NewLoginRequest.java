package com.revature.expanded.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * A DTO stands for 'Data Transfer Object'. It is a design
 * pattern that is used to transfer data between software
 * application subsystems.
 *
 * The idea with a DTO is to provide a flat structure that
 * only contains relevant data from two other domain models
 *
 * In this example, we are combining the 'Id' of an Employee
 * with the username and password.
 *
 * NOTE: I could also represent this with just my EmployeeLogin
 * object, however, the DTO pattern is commonly used outside
 * of direct repository communication (i.e. communication between
 * a client and controller)
 *
 * DTOs are objects that represent the transalation
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewLoginRequest {

    private int employeeId;
    private String username;
    private String password;
}
