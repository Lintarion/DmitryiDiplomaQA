package ru.netology.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitCardTest {

    DashboardPage dashboardPage = new DashboardPage();

    @BeforeEach
    void setup() {
        open(System.getProperty("sut.url"));
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SqlHelper.cleanDataBase();
    }

    @Test
    @DisplayName("Оплата по одобренной дебитовой карте")
    void shouldPayByAppDC() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val approvedCardInformation = DataHelper.getApprovedCardInfo();
        debitCardPage.cardInfo(approvedCardInformation);
        debitCardPage.okNotification();
        val paymentStatus = SqlHelper.getPaymentEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    @DisplayName("Оплата по отклоненной дебитовой карте")
    void shouldPayNotByDecDC() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInfo();
        debitCardPage.cardInfo(declinedCardInformation);
        debitCardPage.nokNotification();
        val paymentStatus = SqlHelper.getPaymentEntity();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с невалидным номером")
    void shouldNotPayByInvNum() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val invalidCardInformation = DataHelper.getInvalidCardInfo();
        debitCardPage.cardInfo(invalidCardInformation);
        debitCardPage.messInvalidCardNumber();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с неполным номером")
    void shouldErrorNotFullNum() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val notFullCardInformation = DataHelper.getNotFullCardInfo();
        debitCardPage.cardInfo(notFullCardInformation);
        debitCardPage.messErrorNum();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с невалидным месяцем")
    void shouldErrorInvalidMonth() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val invalidMonthCardInformation = DataHelper.getInvalidMonthCardInfo();
        debitCardPage.cardInfo(invalidMonthCardInformation);
        debitCardPage.messInvalidMonth();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с указанием истекшего месяца")
    void shouldErrorExpiredMonth() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val expiredMonthCardInformation = DataHelper.getExpiredMonthCardInfo();
        debitCardPage.cardInfo(expiredMonthCardInformation);
        debitCardPage.messExpiredMonth();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с указанием истекшего года")
    void shouldErrorExpiredYear() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInfo();
        debitCardPage.cardInfo(expiredYearCardInformation);
        debitCardPage.messExpiredYearField();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с указанием невалидных значений в поле Владелец")
    void shouldErrorInvalidOwner() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val invalidOwner = DataHelper.getInvalidOwnerCard();
        debitCardPage.cardInfo(invalidOwner);
        debitCardPage.messInvalidOwner();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с указанием невалидных значений в поле Cvc")
    void shouldErrorCvc() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val invalidCvc = DataHelper.getInvalidCvc();
        debitCardPage.cardInfo(invalidCvc);
        debitCardPage.messInvalidCvc();
    }

    @Test
    @DisplayName("Отравка пустой формы")
    void shouldNotSendEmptyForm() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val emptyForm = DataHelper.getEmptyCardInfo();
        debitCardPage.cardInfo(emptyForm);
        debitCardPage.messEmptyCardNumberField();
        debitCardPage.messEmptyMonthField();
        debitCardPage.messEmptyYearField();
        debitCardPage.messEmptyOwnerField();
        debitCardPage.messEmptyCvcField();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Номер карты")
    void shouldErrorEmptyCardNum() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val emptyCardNum = DataHelper.getEmptyCardNumber();
        debitCardPage.cardInfo(emptyCardNum);
        debitCardPage.messEmptyCardNumberField();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Месяц")
    void shouldErrorEmptyMonth() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val emptyMonth = DataHelper.getEmptyMonth();
        debitCardPage.cardInfo(emptyMonth);
        debitCardPage.messEmptyMonthField();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Год")
    void shouldErrorEmptyYear() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val emptyYear = DataHelper.getEmptyYear();
        debitCardPage.cardInfo(emptyYear);
        debitCardPage.messEmptyYearField();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Владелец")
    void shouldErrorEmptyOwner() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val emptyOwner = DataHelper.getEmptyOwner();
        debitCardPage.cardInfo(emptyOwner);
        debitCardPage.messEmptyOwnerField();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Cvc")
    void shouldErrorEmptyCvc() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val emptyCvc = DataHelper.getEmptyCvc();
        debitCardPage.cardInfo(emptyCvc);
        debitCardPage.messEmptyCvcField();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с вводом 000 в поле Cvc")
    void shouldErrorZeroCvc() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val zeroCvc = DataHelper.getZeroCvc();
        debitCardPage.cardInfo(zeroCvc);
        debitCardPage.messInvalidCvc();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с вводом 0 в поле Номер карты")
    void shouldErrorZeroCardNum() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val zeroCardNum = DataHelper.getCardZeroNumber();
        debitCardPage.cardInfo(zeroCardNum);
        debitCardPage.messZeroNum();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с вводом 0 в поле Месяц")
    void shouldErrorZeroMonth() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val zeroMonth = DataHelper.getZeroMonthCardInfo();
        debitCardPage.cardInfo(zeroMonth);
        debitCardPage.messInvalidMonth();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с вводом 0 в поле Год")
    void shouldErrorZeroYear() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val zeroYear = DataHelper.getZeroYearCardInformation();
        debitCardPage.cardInfo(zeroYear);
        debitCardPage.messInvalidYear();
    }
}
