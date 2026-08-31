package com.revature.expanded.services;

import com.revature.expanded.exceptions.PaycheckNotFoundException;
import com.revature.expanded.model.Paycheck;
import com.revature.expanded.repository.PaycheckRepository;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaycheckServiceTest {

    @Mock
    private PaycheckRepository paycheckRepository;

    /*
     * MeterRegistry isn't a repository, but PaycheckService's constructor
     * requires one to build its Counter bean. A real SimpleMeterRegistry is
     * lightweight enough to use directly here instead of mocking it.
     */
    private final MeterRegistry meterRegistry = new SimpleMeterRegistry();

    private PaycheckService paycheckService;

    @BeforeEach
    void setUp() {
        /*
         * Here, we build the service "by hand" rather than with @InjectMocks
         * since the constructor also needs the MeterRegistry. Keep in mind
         * that @InjectMocks only fills in @Mock-annotated fields automatically.
         */
        paycheckService = new PaycheckService(paycheckRepository, meterRegistry);
    }

    @Test
    void getPaycheckById_whenFound_returnsPaycheck() {
        Paycheck paycheck = new Paycheck();
        paycheck.setPaycheckId(1);
        paycheck.setEmpId(1000);
        when(paycheckRepository.findById(1)).thenReturn(Optional.of(paycheck));

        Paycheck result = paycheckService.getPaycheckById(1);

        assertThat(result.getEmpId()).isEqualTo(1000);
        verify(paycheckRepository).findById(1);
    }

    @Test
    void getPaycheckById_whenNotFound_throwsException() {
        when(paycheckRepository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> paycheckService.getPaycheckById(999))
                .isInstanceOf(PaycheckNotFoundException.class);
    }

    @Test
    void getHighEarningPaychecks_delegatesToRepository() {
        BigDecimal threshold = new BigDecimal("5000.00");
        Paycheck paycheck = new Paycheck();
        when(paycheckRepository.findByGrossPayGreaterThan(threshold))
                .thenReturn(List.of(paycheck));

        List<Paycheck> result = paycheckService.getHighEarningPaychecks(threshold);

        assertThat(result).hasSize(1);
        /*
         * verify() confirms the SERVICE called the repository correctly.
         * It says nothing about whether findByGrossPayGreaterThan's actual
         * generated query is correct against a real database.
         */
        verify(paycheckRepository).findByGrossPayGreaterThan(threshold);
    }
}
