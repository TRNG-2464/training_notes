package com.revature.expanded.repository;

import com.revature.expanded.model.Paycheck;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * Note: The '@AutoConfigureTestDatabase' annotation below is actually
 * the default behavior of @DataJpaTest. Replace.ANY means that, no
 * matter what real DataSource is configured for this application, Spring
 * Boot will swap it out for an embedded, in-memory database.
 *
 * If you want to control this (i.e. actually test against your actual
 * database for a full integration test) then you can replace the annotation
 * with 'Replace.NONE' ("use the DataSource that is actually configured")
 *
 * @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class PaycheckRepositoryDataJpaTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PaycheckRepository paycheckRepository;

    /*
     * findById() is inherited from JpaRepository. This test is used
     * to confirm the entity mapping (table name, column names and
     * @GeneratedValue strategy) are correct, rather than any custom
     * query logic
     */
    @Test
    void findById_whenPaycheckExists_returnsIt() {
        Paycheck paycheck = buildPaycheck(1000, new BigDecimal("3200.00"));
        testEntityManager.persistAndFlush(paycheck);

        /*
         * persistAndFlush() causes Hibernate to assign the generated ID
         * (GenerationType.IDENTITY) directly back onto our entity instance,
         * so we can read it immediately instead of running a lookup query.
         */
        Integer generatedId = paycheck.getPaycheckId();

        Optional<Paycheck> result = paycheckRepository.findById(generatedId);

        assertThat(result).isPresent();
        assertThat(result.get().getGrossPay()).isEqualByComparingTo("3200.00");
    }

    /*
     * This test just confirms that the repository returns an empty
     * optional when attempting to find an id that doesn't exist
     */
    @Test
    void findById_whenNoMatchingPaycheck_returnsEmptyOptional() {
        Optional<Paycheck> result = paycheckRepository.findById(999);

        assertThat(result).isEmpty();
    }

    /*
     * This test confirms the derived query method correctly finds
     * employees with an id of 1 (i.e., we did not malform the name
     * of the query method - resulting in an incorrect derived query)
     */
    @Test
    void findByEmpId_returnsOnlyMatchingEmployeesPaychecks() {
        Paycheck paycheckForEmp1000 = buildPaycheck(1000, new BigDecimal("2500.00"));
        Paycheck anotherPaycheckForEmp1000 = buildPaycheck(1000, new BigDecimal("2600.00"));
        Paycheck paycheckForDifferentEmployee = buildPaycheck(2000, new BigDecimal("3000.00"));

        testEntityManager.persistAndFlush(paycheckForEmp1000);
        testEntityManager.persistAndFlush(anotherPaycheckForEmp1000);
        testEntityManager.persistAndFlush(paycheckForDifferentEmployee);

        List<Paycheck> result = paycheckRepository.findByEmpId(1000);

        assertThat(result).hasSize(2);
        assertThat(result).allMatch(p -> p.getEmpId().equals(1000));
    }

    /*
     * This method verifies that the GrossPayGreaterThan derived query method
     */
    @Test
    void findByGrossPayGreaterThan_excludesPaychecksAtOrBelowThreshold() {
        testEntityManager.persistAndFlush(buildPaycheck(1000, new BigDecimal("4000.00")));
        testEntityManager.persistAndFlush(buildPaycheck(1000, new BigDecimal("5000.00")));
        testEntityManager.persistAndFlush(buildPaycheck(1000, new BigDecimal("6000.00")));

        List<Paycheck> result = paycheckRepository.findByGrossPayGreaterThan(new BigDecimal("5000.00"));

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getGrossPay()).isEqualByComparingTo("6000.00");
    }

    /*
     * Verifies the composition of a compound derived query method
     * ("After" on the PayFromDate field and "Before" on the PayToDate)
     */
    @Test
    void findByPayFromDateAfterAndPayToDateBefore_returnsOnlyPaychecksStrictlyWithinRange() {
        Paycheck insideRange = buildPaycheck(1000, new BigDecimal("3000.00"));
        insideRange.setPayFromDate(LocalDate.of(2026, 8, 5));
        insideRange.setPayToDate(LocalDate.of(2026, 8, 10));

        Paycheck outsideRange = buildPaycheck(1000, new BigDecimal("3000.00"));
        outsideRange.setPayFromDate(LocalDate.of(2026, 7, 1));
        outsideRange.setPayToDate(LocalDate.of(2026, 7, 15));

        testEntityManager.persistAndFlush(insideRange);
        testEntityManager.persistAndFlush(outsideRange);

        List<Paycheck> result = paycheckRepository.findByPayFromDateAfterAndPayToDateBefore(
                LocalDate.of(2026, 8, 1), LocalDate.of(2026, 8, 15));

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getPayFromDate()).isEqualTo(LocalDate.of(2026, 8, 5));
    }

    /*
     * Note that we have not created a @AfterEach teardown method to
     * clear our test database. By default, Spring rolls back
     * transactions
     */
    private Paycheck buildPaycheck(Integer empId, BigDecimal grossPay) {
        Paycheck paycheck = new Paycheck();
        paycheck.setEmpId(empId);
        paycheck.setPayFromDate(LocalDate.of(2026, 8, 1));
        paycheck.setPayToDate(LocalDate.of(2026, 8, 15));
        paycheck.setHoursWorked(new BigDecimal("80.00"));
        paycheck.setGrossPay(grossPay);
        paycheck.setTotalDeductions(new BigDecimal("500.00"));
        paycheck.setNetPay(grossPay.subtract(new BigDecimal("500.00")));
        return paycheck;
    }
}
