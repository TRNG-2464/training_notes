package com.revature.expanded.services;

import com.revature.expanded.exceptions.PaycheckNotFoundException;
import com.revature.expanded.model.Paycheck;
import com.revature.expanded.repository.PaycheckRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaycheckService {

    private final PaycheckRepository paycheckRepository;
    private final Counter paychecksCreatedCounter;

    public PaycheckService(PaycheckRepository paycheckRepository, MeterRegistry meterRegistry) {
        this.paycheckRepository = paycheckRepository;
        this.paychecksCreatedCounter = Counter.builder("paychecks.created")
                .description("Total number of paychecks created via the API")
                .register(meterRegistry);
    }

    // Simple use of @PreAuthorize - Only Admins can view all paychecks
    // "ROLE_ADMIN"
    @PreAuthorize("hasRole('ADMIN')")
    public List<Paycheck> getAllPaychecks() {
        return paycheckRepository.findAll();
    }

    /*
     * The @PreAuthorize annotation is loaded with a Context that Spring
     * Security Builds: MethodSecurityExpressionRoot
     *
     * This context is why we can call methods like 'hasRole'
     *
     * The '@' is used to reference a bean
     *
     * The '#' on empId is referencing the id passed to this method (i.e.
     * the argument given to this method)
     *
     * Here the 'PreAuthorize' annotation evaluates one of three conditions:
     *  does the user have the Role: ROLE_ADMIN or ROLE_MANAGER? or is this
     *  user attempting to view their own paychecks?
     *
     * In this case 'authentication.name' refers to the currently authenticated
     * user's username
     */
    @PreAuthorize("hasRole('ADMIN') " +
            "or hasRole('MANAGER') " +
            "or @employeeLoginService.isOwnRecord(authentication.name, #empId)")
    public List<Paycheck> getPaychecksByEmployee(Integer empId) {
        return paycheckRepository.findByEmpId(empId);
    }


    public Page<Paycheck> getPaychecksPaged(Pageable pageable) {
        return paycheckRepository.findAll(pageable);
    }

    public Paycheck getPaycheckById(Integer id) {
        return paycheckRepository.findById(id)
                .orElseThrow(() -> new PaycheckNotFoundException(id));
    }

    public List<Paycheck> getPaychecksForEmployeeSortedByNetPay(Integer empId) {
        return paycheckRepository.findByEmpIdOrderByNetPayDesc(empId);
    }

    public List<Paycheck> getHighEarningPaychecks(BigDecimal minGrossPay) {
        return paycheckRepository.findByGrossPayGreaterThan(minGrossPay);
    }

    public Paycheck createPaycheck(Paycheck paycheck) {
        Paycheck saved = paycheckRepository.save(paycheck);
        paychecksCreatedCounter.increment();
        return saved;
    }

    @Transactional
    public Paycheck createValidatedPaycheck(Paycheck paycheck) {
        if (paycheck.getGrossPay().compareTo(paycheck.getNetPay()) < 0) {
            throw new IllegalStateException("Gross pay cannot be less than net pay");
        }
        return paycheckRepository.save(paycheck);
    }

    @Transactional(rollbackFor = Exception.class)
    public Paycheck createPaycheckStrict(Paycheck paycheck) throws Exception {
        if (paycheck.getHoursWorked() == null) {
            throw new Exception("Hours worked is required");
        }
        return paycheckRepository.save(paycheck);
    }
}
