package com.revature.basics.controllers;

import com.revature.basics.model.Employee;
import com.revature.basics.repository.EmployeeRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/basics/broken", method = RequestMethod.GET)
    public String brokenView() {
        // Note: This will cause an error because I don't have any
        // 'views' registered on my application named 'employee-view'
        // We won't be covering how to do this in Java since we are
        // create a REST api
        return "employee-view";
    }

    /*
     * Note: If you want the ResponseBody to produce a different type
     * you can explicitly reference the MediaType within a Request method
     * annotation, such as 'RequestMapping' or 'GetMapping':
     *      @GetMapping(value = "endpoint...", produces = MediaType.SomeTypeHere)
     *
     * Note: For your REST APIs just using a ResponseBody is sufficient
     *      i.e. returning data as a JSON in the response's body)
     */
    @RequestMapping(value = "/basics/view",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String plainView() {
        return "<h1>Basic View</h1><p>This was returned from my Spring App as a Basic/plain view</p>";
    }

    // ReponseBody serializes the response data into the body of the response
    // This will return the list of Employees as a JSON
    @RequestMapping(value = "/basics/employee-data", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getEmployeeData() {
        return employeeRepository.findAll();
    }


}
