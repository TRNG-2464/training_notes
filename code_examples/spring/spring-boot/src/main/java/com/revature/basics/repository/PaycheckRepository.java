package com.revature.basics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.basics.model.Paycheck;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PaycheckRepository extends JpaRepository<Paycheck, Integer> {

    /*
     * Custom query method. Spring Data JPA generates the implementation
     * automatically based on the method name alone. It does so by using
     * the naming convention as a standard:
     * "findBy<EntityFieldName>" | The Paycheck entity has an 'EmpId' field
     */
    List<Paycheck> findByEmpId(Integer empId);

    /*
     * Custom query method using a numeric (Greater than) comparison
     */
    List<Paycheck> findByGrossPayGreaterThan(BigDecimal minGrossPay);

    /*
     * This query method combines a date Range using two property
     * expressions with 'AND':
     *      -PayFromDate | PayToDate - fields
     *      -After | Before - expressions
     */
    List<Paycheck> findByPayFromDateAfterAndPayToDateBefore(LocalDate afterDate, LocalDate beforeDate);

    /*
     * Query Method showcasing OrderBy -this will sort the results by
     * netPay in descending order.
     */
    List<Paycheck> findByEmpIdOrderByNetPayDesc(Integer empId);
}
