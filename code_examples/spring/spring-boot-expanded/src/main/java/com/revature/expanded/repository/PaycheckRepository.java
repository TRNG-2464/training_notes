package com.revature.expanded.repository;

import com.revature.expanded.model.Paycheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PaycheckRepository extends JpaRepository<Paycheck, Integer> {
    List<Paycheck> findByEmpId(Integer empId);
    List<Paycheck> findByGrossPayGreaterThan(BigDecimal minGrossPay);
    List<Paycheck> findByPayFromDateAfterAndPayToDateBefore(LocalDate afterDate, LocalDate beforeDate);
    List<Paycheck> findByEmpIdOrderByNetPayDesc(Integer empId);
}
