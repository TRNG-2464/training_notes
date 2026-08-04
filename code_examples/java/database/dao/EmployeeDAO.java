package com.revature.database.dao;

import com.revature.database.models.Employee;
import java.util.List;

public interface EmployeeDAO {
    public Employee selectEmployeeByName(String name);
    public List<Employee> selectAllEmployees();

    public Boolean insertIntoEmployees(Employee emp);
    public Boolean updateEmployeeByName(String name, Employee emp);
    public Boolean deleteEmployeeByName(String name);
}
