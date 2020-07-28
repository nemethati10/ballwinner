package com.promotion.ballwinner.bm.boundary;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.promotion.ballwinner.bm.control.WinnerStatisticsBA;
import com.promotion.ballwinner.bm.model.WinnerStatisticResponse;

/**
 * Boundary class for retrieving stats about winners.
 */
@Service
public class WinnerStatisticsBF {

    private WinnerStatisticsBA winnerStatisticBA;

    public WinnerStatisticsBF(final WinnerStatisticsBA winnerStatisticBA) {
        this.winnerStatisticBA = winnerStatisticBA;
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
    public List<WinnerStatisticResponse> getStatistics(final int pageNumber, final int pageSize,
            final LocalDate startDate, final LocalDate endDate) {
        return winnerStatisticBA.createWinnerStatistics(pageNumber, pageSize, startDate, endDate);
    }
}
