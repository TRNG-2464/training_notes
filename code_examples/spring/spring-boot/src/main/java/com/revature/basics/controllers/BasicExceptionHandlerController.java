package com.revature.basics.controllers;

import com.revature.basics.exceptions.OtherEmployeeNotFoundException;
import com.revature.basics.exceptions.SimpleEmployeeNotFoundException;
import com.revature.basics.model.Employee;
import com.revature.basics.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/*
 * This Controller leverages basic ExceptionHandler and
 * custom exception (see SimpleEmployeeNotFoundException)
 *
 * Note: a better approach is to use a Global exception
 * handler - see "" for more details
 */
@RestController
@RequestMapping("/except")
public class BasicExceptionHandlerController {

    private final EmployeeRepository employeeRepository;

    public BasicExceptionHandlerController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Throws our SimpleEmployeeNotFoundException that is annotated with
    // the @ResponseStatus
    @GetMapping("/simple/{id}")
    public Employee getEmployeeByIdSimple(@PathVariable Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new SimpleEmployeeNotFoundException(id));
    }

    // Throws our OtherEmployeeNotFoundException - it has no annotations
    // Instead the 'handleNotFoundLocally' method would handle this
    // exception being thrown...
    @GetMapping("/other/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new OtherEmployeeNotFoundException(id));
    }

    /*
     * The following Method is called when an 'OtherEmployeeNotFoundException'
     * is thrown from any method in this controller.
     *
     * You can use @ExceptionHandler and @ResponseStatus on a local
     * method within a controller to handle specific Exceptions
     * with a defined HTTP status.
     *
     * However, this is not the best solution, since it only maps the
     * specific handler logic within that single controller. All other
     * controllers would then have to include their own logic. This
     * can cause maintainability issues in your application
     *
     * Instead, it is better to use a Global Exception Handler
     */
    @ExceptionHandler(OtherEmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundLocally(OtherEmployeeNotFoundException ex) {
        return ex.getMessage();
    }
}
