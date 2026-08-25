package com.revature.basics.controllers;

import com.revature.basics.model.Employee;
import com.revature.basics.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmployeeBasicController {

    private final EmployeeRepository employeeRepository;

    public EmployeeBasicController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/basics/broken")
    public String brokenView() {
        // Note: This will cause a whitelabel error because
        // I don't have any 'views' registered on my application
        // named 'employee-view' - we won't be covering how to do
        // this in Java, but we could use a library called 'Thymeleaf'
        return "employee-view";
    }

    /*
     * Note: If you want the ResponseBody to produce a different type
     * you can explicitly reference the MediaType within the 'GetMapping'
     * annotation:
     *  @GetMapping(value = "endpoint...", produces = MediaType.SomeTypeHere)
     */
    @RequestMapping("/basics/employee-data")
    @ResponseBody
    public List<Employee> getEmployeeData() {
        return employeeRepository.findAll();
    }

    @RequestMapping(value = "/basics/view", method = RequestMethod.GET)
    @ResponseBody
    public String plainView() {
        return "<h1>Basic View</h1><p>This was returned from my Spring App as a Basic/plain view</p>";
    }
}
