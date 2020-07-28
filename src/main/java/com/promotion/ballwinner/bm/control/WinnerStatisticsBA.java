package com.promotion.ballwinner.bm.control;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.promotion.ballwinner.bm.control.dao.ManageLogEntryBA;
import com.promotion.ballwinner.bm.model.WinnerStatisticResponse;

/**
 * Service providing statistics regarding the results of the campaign.
 */
@Service
public class WinnerStatisticsBA {

    private ManageLogEntryBA manageLogEntryBA;

    public WinnerStatisticsBA(final ManageLogEntryBA manageLogEntryBA) {
        this.manageLogEntryBA = manageLogEntryBA;
    }

    /**
     * Return stats about winners in the followig format: <coupon_code> <email> <territory>
     *
     * @param pageNumber
     * @param pageSize
     * @param startDate
     * @param endDate
     *
     * @return
     */
    public List<WinnerStatisticResponse> createWinnerStatistics(final int pageNumber, final int pageSize,
            final LocalDate startDate, final LocalDate endDate) {

        final Pageable page = PageRequest.of(pageNumber, pageSize);
        return manageLogEntryBA.findAllWinnerByPeriod(page, startDate, endDate);
    }
}
