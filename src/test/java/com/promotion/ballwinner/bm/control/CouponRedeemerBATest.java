package com.promotion.ballwinner.bm.control;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.promotion.ballwinner.bm.entity.AddressBE;
import com.promotion.ballwinner.bm.entity.CustomerBE;
import com.promotion.ballwinner.bm.entity.LogEntryBE;
import com.promotion.ballwinner.bm.entity.RedeemResult;
import com.promotion.ballwinner.bm.entity.RuleBE;
import com.promotion.ballwinner.bm.entity.TerritoryBE;
import com.promotion.ballwinner.bm.util.CouponUtil;

/**
 * Test suite for {@link CouponRedeemerBA}
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan({ "com.promotion.ballwinner.bm.*" })
class CouponRedeemerBATest {

    private static final String TERRITORY_DE = "GERMANY";
    private static final String TERRITORY_HU = "HUNGARY";

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CouponRedeemerBA couponRedeemerBA;

    @Test
    void testRedeemCouponWinnerWithTwoTerritories() {

        // given
        final AddressBE address = new AddressBE();
        address.setCountry(TERRITORY_HU);

        for (int i = 0; i < 79; i++) {
            final LogEntryBE logEntry = new LogEntryBE();
            logEntry.setTerritory(TERRITORY_HU);
            logEntry.setSubmittedDate(LocalDate.now());
            logEntry.setWinner(false);
            logEntry.setCouponCode(CouponUtil.generateCouponCode());
            testEntityManager.persist(logEntry);
        }

        for (int i = 0; i < 39; i++) {
            final LogEntryBE logEntry = new LogEntryBE();
            logEntry.setTerritory(TERRITORY_DE);
            logEntry.setSubmittedDate(LocalDate.now());
            logEntry.setWinner(false);
            logEntry.setCouponCode(CouponUtil.generateCouponCode());
            testEntityManager.persist(logEntry);
        }

        final CustomerBE customerHu = new CustomerBE();
        customerHu.setCouponCode(CouponUtil.generateCouponCode());
        customerHu.setName("Attila");
        customerHu.setEmail("nemethati10@gmail.com");
        customerHu.setAge(25);
        customerHu.setAddress(address);

        final CustomerBE customerDe = new CustomerBE();
        customerDe.setCouponCode(CouponUtil.generateCouponCode());
        customerDe.setName("Christian");
        customerDe.setEmail("metzc@gmail.com");
        customerDe.setAge(25);
        customerDe.setAddress(address);

        TerritoryBE hungary = new TerritoryBE();
        hungary.setTerritoryName(TERRITORY_HU);

        RuleBE hungaryRule = new RuleBE();
        hungaryRule.setTerritory(hungary);
        hungaryRule.setNthEntry(80);
        hungaryRule.setNumberOfProducts(5000);
        hungaryRule.setMaximumWinnersPerDay(100);

        hungaryRule = testEntityManager.persist(hungaryRule);
        hungary = testEntityManager.persist(hungary);
        hungary.setRule(hungaryRule);

        TerritoryBE germany = new TerritoryBE();
        germany.setTerritoryName(TERRITORY_DE);

        RuleBE germanyRule = new RuleBE();
        germanyRule.setTerritory(germany);
        germanyRule.setNthEntry(40);
        germanyRule.setNumberOfProducts(10000);
        germanyRule.setMaximumWinnersPerDay(250);

        germanyRule = testEntityManager.persist(germanyRule);
        germany = testEntityManager.persist(germany);
        germany.setRule(germanyRule);

        // when
        final RedeemResult resultHun = couponRedeemerBA.redeemCoupon(customerHu, TERRITORY_HU);
        final RedeemResult resultDe = couponRedeemerBA.redeemCoupon(customerDe, TERRITORY_DE);

        // then
        assertEquals(RedeemResult.WINNER, resultHun);
        assertEquals(RedeemResult.WINNER, resultDe);
    }

    @Test
    void testRedeemCouponWinner() {

        // given
        final AddressBE address = new AddressBE();
        address.setCountry(TERRITORY_HU);

        for (int i = 0; i < 79; i++) {
            final LogEntryBE logEntry = new LogEntryBE();
            logEntry.setTerritory(TERRITORY_HU);
            logEntry.setSubmittedDate(LocalDate.now());
            logEntry.setWinner(false);
            logEntry.setCouponCode(CouponUtil.generateCouponCode());
            testEntityManager.persist(logEntry);
        }

        final CustomerBE customer = new CustomerBE();
        customer.setCouponCode(CouponUtil.generateCouponCode());
        customer.setName("Attila");
        customer.setEmail("nemethati10@gmail.com");
        customer.setAge(25);
        customer.setAddress(address);

        TerritoryBE hungary = new TerritoryBE();
        hungary.setTerritoryName(TERRITORY_HU);

        RuleBE hungaryRule = new RuleBE();
        hungaryRule.setTerritory(hungary);
        hungaryRule.setNthEntry(80);
        hungaryRule.setNumberOfProducts(5000);
        hungaryRule.setMaximumWinnersPerDay(100);

        hungaryRule = testEntityManager.persist(hungaryRule);
        hungary = testEntityManager.persist(hungary);
        hungary.setRule(hungaryRule);

        // when
        final RedeemResult result = couponRedeemerBA.redeemCoupon(customer, TERRITORY_HU);

        // then
        assertEquals(RedeemResult.WINNER, result);
    }

    @Test
    void testRedeemCouponLooserBecauseWinnersLimitReached() {

        // given
        final AddressBE address = new AddressBE();
        address.setCountry(TERRITORY_HU);

        for (int i = 0; i < 100; i++) {
            final LogEntryBE logEntry = new LogEntryBE();
            logEntry.setTerritory(TERRITORY_HU);
            logEntry.setSubmittedDate(LocalDate.now());
            logEntry.setWinner(true);
            logEntry.setCouponCode(CouponUtil.generateCouponCode());

            testEntityManager.persist(logEntry);

        }
        final CustomerBE customer = new CustomerBE();
        customer.setCouponCode(CouponUtil.generateCouponCode());
        customer.setName("Attila");
        customer.setEmail("nemethati10@gmail.com");
        customer.setAge(25);
        customer.setAddress(address);

        TerritoryBE hungary = new TerritoryBE();
        hungary.setTerritoryName(TERRITORY_HU);
        //hungary.setCampaign(campaign);

        RuleBE hungaryRule = new RuleBE();
        hungaryRule.setTerritory(hungary);
        hungaryRule.setNthEntry(80);
        hungaryRule.setNumberOfProducts(5000);
        hungaryRule.setMaximumWinnersPerDay(100);

        hungaryRule = testEntityManager.persist(hungaryRule);
        hungary = testEntityManager.persist(hungary);
        hungary.setRule(hungaryRule);

        // when
        final RedeemResult result = couponRedeemerBA.redeemCoupon(customer, TERRITORY_HU);

        // then
        assertEquals(RedeemResult.BETTER_LUCK_NEXT_TIME, result);
    }

    @Test
    void testRedeemCouponLooser() {

        // given
        final AddressBE address = new AddressBE();
        address.setCountry(TERRITORY_HU);

        final CustomerBE customer = new CustomerBE();
        customer.setCouponCode(CouponUtil.generateCouponCode());
        customer.setName("Attila");
        customer.setEmail("nemethati10@gmail.com");
        customer.setAge(25);
        customer.setAddress(address);

        TerritoryBE hungary = new TerritoryBE();
        hungary.setTerritoryName(TERRITORY_HU);
        //hungary.setCampaign(campaign);

        RuleBE hungaryRule = new RuleBE();
        hungaryRule.setTerritory(hungary);
        hungaryRule.setNthEntry(80);
        hungaryRule.setNumberOfProducts(5000);
        hungaryRule.setMaximumWinnersPerDay(100);

        hungaryRule = testEntityManager.persist(hungaryRule);
        hungary = testEntityManager.persist(hungary);
        hungary.setRule(hungaryRule);

        // when
        final RedeemResult result = couponRedeemerBA.redeemCoupon(customer, TERRITORY_HU);

        // then
        assertEquals(RedeemResult.BETTER_LUCK_NEXT_TIME, result);

    }

    @Test
    void testRedeemInvalidCoupon() {

        // given
        final AddressBE address = new AddressBE();
        address.setCountry(TERRITORY_HU);

        final CustomerBE customer = new CustomerBE();
        customer.setCouponCode("INVALID");
        customer.setName("Attila");
        customer.setEmail("nemethati10@gmail.com");
        customer.setAge(25);
        customer.setAddress(address);

        // when
        final Exception exception = assertThrows(IllegalStateException.class, () -> {
            couponRedeemerBA.redeemCoupon(customer, TERRITORY_HU);
        });

        final String expectedMessage = "Invalid input data!";
        final String actualMessage = exception.getMessage();

        // then
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testRedeemInvalidEmail() {

        // given
        final AddressBE address = new AddressBE();
        address.setCountry(TERRITORY_HU);

        final CustomerBE customer = new CustomerBE();
        customer.setCouponCode(CouponUtil.generateCouponCode());
        customer.setName("Attila");
        customer.setEmail("nemethati10gmail.com");
        customer.setAge(25);
        customer.setAddress(address);

        // when
        final Exception exception = assertThrows(IllegalStateException.class, () -> {
            couponRedeemerBA.redeemCoupon(customer, TERRITORY_HU);
        });

        final String expectedMessage = "Invalid input data!";
        final String actualMessage = exception.getMessage();

        // then
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testRedeemInvalidAge() {

        // given
        final AddressBE address = new AddressBE();
        address.setCountry(TERRITORY_HU);

        final CustomerBE customer = new CustomerBE();
        customer.setCouponCode(CouponUtil.generateCouponCode());
        customer.setName("Attila");
        customer.setEmail("nemethati10@gmail.com");
        customer.setAge(10);
        customer.setAddress(address);

        // when
        final Exception exception = assertThrows(IllegalStateException.class, () -> {
            couponRedeemerBA.redeemCoupon(customer, TERRITORY_HU);
        });

        final String expectedMessage = "Invalid input data!";
        final String actualMessage = exception.getMessage();

        // then
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testRedeemCouponAlreadyExists() {

        // given
        final String coupon = CouponUtil.generateCouponCode();
        final AddressBE address = new AddressBE();
        address.setCountry(TERRITORY_HU);

        final LogEntryBE logEntry = new LogEntryBE();
        logEntry.setTerritory(TERRITORY_HU);
        logEntry.setSubmittedDate(LocalDate.now());
        logEntry.setWinner(true);
        logEntry.setCouponCode(coupon);

        testEntityManager.persist(logEntry);

        final CustomerBE customer = new CustomerBE();
        customer.setCouponCode(coupon);
        customer.setName("Attila");
        customer.setEmail("nemethati10@gmail.com");
        customer.setAge(25);
        customer.setAddress(address);

        // when
        final Exception exception = assertThrows(IllegalStateException.class, () -> {
            couponRedeemerBA.redeemCoupon(customer, TERRITORY_HU);
        });

        final String expectedMessage = "Invalid input data!";
        final String actualMessage = exception.getMessage();

        // then
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
