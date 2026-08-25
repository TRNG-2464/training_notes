package com.revature.basics.controllers;

import com.revature.basics.model.Paycheck;
import com.revature.basics.services.PaycheckService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/paychecks")
public class PaycheckController {

    private final PaycheckService paycheckService;

    public PaycheckController(PaycheckService paycheckService) {
        this.paycheckService = paycheckService;
    }

    // GET http://localhost:8080/paychecks
    @GetMapping
    public ResponseEntity<List<Paycheck>> getAllPaychecks() {
        return ResponseEntity.ok(paycheckService.getAllPaychecks());
    }


    // GET http://localhost:8080/paychecks/paged?page=0&size=5&sort=grossPay,desc
    // The query parameters ('page' / 'size' / 'sort' are used by Spring to
    // automatically perform pagination by creating a 'Pageable' object
    @GetMapping("/paged")
    public ResponseEntity<Page<Paycheck>> getPaychecksPaged(Pageable pageable) {
        return ResponseEntity.ok(paycheckService.getPaychecksPaged(pageable));
    }

    // GET http://localhost:8080/paychecks/1
    @GetMapping("/{id}")
    public ResponseEntity<Paycheck> getPaycheckById(@PathVariable Integer id) {
        return ResponseEntity.ok(paycheckService.getPaycheckById(id));
    }

    // GET http://localhost:8080/paychecks/employee/1000
    @GetMapping("/employee/{empId}")
    public ResponseEntity<List<Paycheck>> getPaychecksByEmployee(@PathVariable Integer empId) {
        return ResponseEntity.ok(paycheckService.getPaychecksByEmployee(empId));
    }

    // GET http://localhost:8080/paychecks/employee/1000/by-netpay
    @GetMapping("/employee/{empId}/by-netpay")
    public ResponseEntity<List<Paycheck>> getPaychecksSortedByNetPay(@PathVariable Integer empId) {
        return ResponseEntity.ok(paycheckService.getPaychecksForEmployeeSortedByNetPay(empId));
    }

    @GetMapping("/high-earning")
    public ResponseEntity<List<Paycheck>> getHighEarningPaychecks(@RequestParam BigDecimal minGrossPay) {
        return ResponseEntity.ok(paycheckService.getHighEarningPaychecks(minGrossPay));
    }

    // POST http://localhost:8080/paychecks
    @PostMapping
    public ResponseEntity<Paycheck> createPaycheck(@RequestBody Paycheck paycheck) {
        Paycheck saved = paycheckService.createPaycheck(paycheck);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
