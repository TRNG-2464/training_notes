package com.revature.basics.services;

import com.revature.basics.exceptions.PaycheckNotFoundException;
import com.revature.basics.model.Paycheck;
import com.revature.basics.repository.PaycheckRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        /*
         * This Example showcases how we can create a Counter metric for
         * Actuator.
         */
        this.paychecksCreatedCounter = Counter.builder("paychecks.created")
                .description("Total number of paychecks created via the API")
                .register(meterRegistry);
    }

    public List<Paycheck> getAllPaychecks() {
        return paycheckRepository.findAll();
    }

    public Page<Paycheck> getPaychecksPaged(Pageable pageable) {
        // findAll(Pageable) - inherited from PagingAndSortingRepository,
        // via JpaRepository. Spring builds the Pageable object
        // automatically from query parameters like "page", "size", and
        // "sort" -- no manual parsing required.
        return paycheckRepository.findAll(pageable);
    }

    public Paycheck getPaycheckById(Integer id) {
        return paycheckRepository.findById(id)
                .orElseThrow(() -> new PaycheckNotFoundException(id));
    }

    public List<Paycheck> getPaychecksByEmployee(Integer empId) {
        return paycheckRepository.findByEmpId(empId);
    }

    public List<Paycheck> getPaychecksForEmployeeSortedByNetPay(Integer empId) {
        return paycheckRepository.findByEmpIdOrderByNetPayDesc(empId);
    }

    public List<Paycheck> getHighEarningPaychecks(BigDecimal minGrossPay) {
        return paycheckRepository.findByGrossPayGreaterThan(minGrossPay);
    }

    public Paycheck createPaycheck(Paycheck paycheck) {
        Paycheck saved = paycheckRepository.save(paycheck);

        /*
         * We increment the counter only after a successful creation
         */
        paychecksCreatedCounter.increment();
        return saved;
    }

    /*
     * This simple example is used to illustrate '@Transactional' on
     * a method. Note: this only performs a single Transaction on the
     * database (save) so it is not required to use @Transactional here
     */
    @Transactional
    public Paycheck createValidatedPaycheck(Paycheck paycheck) {
        if (paycheck.getGrossPay().compareTo(paycheck.getNetPay()) < 0) {
            // This will trigger an automatic rollback of anything
            // else this method may have already done.
            throw new IllegalStateException("Gross pay cannot be less than net pay");
        }
        return paycheckRepository.save(paycheck);
    }

    // Shown ONLY to illustrate the checked-exception exception to the
    // default rollback rule
    @Transactional(rollbackFor = Exception.class)
    // Without "rollbackFor" specified above, a checked Exception thrown
    // from this method would NOT trigger a rollback by default --
    // rollbackFor explicitly widens the rule to include it.
    public Paycheck createPaycheckStrict(Paycheck paycheck) throws Exception {
        if (paycheck.getHoursWorked() == null) {
            throw new Exception("Hours worked is required"); // a CHECKED exception
        }
        return paycheckRepository.save(paycheck);
    }
}
