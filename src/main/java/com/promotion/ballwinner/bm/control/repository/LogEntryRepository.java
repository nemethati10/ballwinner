package com.promotion.ballwinner.bm.control.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.promotion.ballwinner.bm.entity.LogEntryBE;
import com.promotion.ballwinner.bm.model.WinnerStatisticResponse;

/**
 * Repository for {@link LogEntryBE}
 */
@Repository
public interface LogEntryRepository extends CrudRepository<LogEntryBE, Long> {

    boolean existsByCouponCode(final String code);

    @Query("SELECT COUNT(le) FROM LogEntryBE le WHERE le.submittedDate = :localDate and le.territory = :territory")
    long countByDateAndTerritory(final LocalDate localDate, final String territory);

    @Query("SELECT COUNT(le) FROM LogEntryBE le WHERE le.isWinner = true and le.submittedDate = :localDate and le.territory = :territory")
    long countTodayWinnersByTerritory(final LocalDate localDate, final String territory);

    @Query(value = "select new com.promotion.ballwinner.bm.model.WinnerStatisticResponse(email, couponCode, territory) from LogEntryBE  where is_winner = true and submitted_date between ?1 and ?2 group by territory, email, coupon_code")
    List<WinnerStatisticResponse> findAllWinnerByPeriodGroupedByTerritory(final Pageable page,
            final LocalDate startDate, final LocalDate endDate);

}
