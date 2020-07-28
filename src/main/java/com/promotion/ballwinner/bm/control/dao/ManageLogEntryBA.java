package com.promotion.ballwinner.bm.control.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.promotion.ballwinner.bm.control.repository.LogEntryRepository;
import com.promotion.ballwinner.bm.entity.CustomerBE;
import com.promotion.ballwinner.bm.entity.LogEntryBE;
import com.promotion.ballwinner.bm.model.WinnerStatisticResponse;

/**
 * Business activity for managing {@link LogEntryBE}
 */
@Service
public class ManageLogEntryBA {

    private LogEntryRepository logEntryRepository;

    public ManageLogEntryBA(final LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }

    /**
     * Create log entry with customer data.
     */
    public LogEntryBE create(final CustomerBE customer, final String territory) {
        final LogEntryBE logEntry = new LogEntryBE();
        logEntry.setTerritory(territory);
        logEntry.setEmail(customer.getEmail());
        logEntry.setCouponCode(customer.getCouponCode());
        logEntry.setSubmittedDate(LocalDate.now());

        return logEntryRepository.save(logEntry);
    }

    public boolean existsByCode(final String code) {
        return logEntryRepository.existsByCouponCode(code);
    }

    public List<WinnerStatisticResponse> findAllWinnerByPeriod(final Pageable page, final LocalDate startDate,
            final LocalDate endDate) {
        return logEntryRepository.findAllWinnerByPeriodGroupedByTerritory(page, startDate, endDate);
    }

    public long countTodayEntriesByTerritory(final String territory) {
        return logEntryRepository.countByDateAndTerritory(LocalDate.now(), territory);
    }

    public long countTodayWinnersByTerritory(final String territory) {
        return logEntryRepository.countTodayWinnersByTerritory(LocalDate.now(), territory);
    }
}
