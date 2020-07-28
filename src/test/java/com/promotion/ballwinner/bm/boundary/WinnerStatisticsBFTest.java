package com.promotion.ballwinner.bm.boundary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.promotion.ballwinner.bm.entity.LogEntryBE;
import com.promotion.ballwinner.bm.model.WinnerStatisticResponse;
import com.promotion.ballwinner.bm.util.CouponUtil;

/**
 * Test suite for {@link WinnerStatisticsBF} class.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan({ "com.promotion.ballwinner.bm.*" })
public class WinnerStatisticsBFTest {

    private static final int NUMBER_OF_ENTRIES = 50;
    private static final String TERRITORY_DE = "GERMANY";
    private static final String TERRITORY_HU = "HUNGARY";

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private WinnerStatisticsBF winnerStatisticsBF;

    @Before
    public void setUp() {

        // given
        for (int i = 0; i < NUMBER_OF_ENTRIES; i++) {

            final LogEntryBE logEntry = new LogEntryBE();
            logEntry.setEmail("nemethati" + i + "@gmail.com");
            logEntry.setCouponCode(CouponUtil.generateCouponCode());
            logEntry.setSubmittedDate(LocalDate.now());

            // half of the entries will be winner
            if (i % 2 == 0) {
                logEntry.setWinner(true);
            }

            // half of the entries will be from one territory, half from the other
            if (i < NUMBER_OF_ENTRIES / 2) {
                logEntry.setTerritory(TERRITORY_HU);
            } else {
                logEntry.setTerritory(TERRITORY_DE);
            }

            testEntityManager.persist(logEntry);
        }

    }

    @Test
    public void testWinnerStatisticsService() {

        // given
        final int page = 0;
        final int pageSize = NUMBER_OF_ENTRIES;
        final LocalDate startDate = LocalDate.now();
        final LocalDate endDate = LocalDate.now();

        // when
        final List<WinnerStatisticResponse> resultStats =
                winnerStatisticsBF.getStatistics(page, pageSize, startDate, endDate);

        final long hungarianWinners = resultStats.stream().filter(response -> {
            return response.getTerritory().equals(TERRITORY_HU);
        }).count();

        final long germanWinners = resultStats.stream().filter(response -> {
            return response.getTerritory().equals(TERRITORY_DE);
        }).count();

        // then
        assertThat(resultStats.size() == NUMBER_OF_ENTRIES / 2);
        assertTrue(hungarianWinners == NUMBER_OF_ENTRIES / 4 + 1);
        assertTrue(germanWinners == NUMBER_OF_ENTRIES / 4);
    }

    @Test
    public void testWinnerStatisticsServiceDateFilterIsOutOfRange() {

        // given
        final int page = 0;
        final int pageSize = NUMBER_OF_ENTRIES;
        final LocalDate startDate = LocalDate.now().plusMonths(1);
        final LocalDate endDate = LocalDate.now().plusMonths(1);

        // when
        final List<WinnerStatisticResponse> resultStats =
                winnerStatisticsBF.getStatistics(page, pageSize, startDate, endDate);

        // then
        assertThat(resultStats.size() == 0);
    }

    @Test
    public void testWinnerStatisticsServicePageSizeIs1() {

        // given
        final int page = 0;
        final int pageSize = 1;
        final LocalDate startDate = LocalDate.now();
        final LocalDate endDate = LocalDate.now();

        // when
        final List<WinnerStatisticResponse> resultStats =
                winnerStatisticsBF.getStatistics(page, pageSize, startDate, endDate);

        // then
        assertTrue(resultStats.size() == 1);
    }
}
