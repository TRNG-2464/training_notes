package com.revature.expanded.repository;

import com.revature.expanded.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByEmpTitleIgnoreCase(String title);

    @Query("SELECT e FROM Employee e WHERE e.empSalary > :minSalary")
    List<Employee> findHighEarners(@Param("minSalary") BigDecimal minSalary);
}
