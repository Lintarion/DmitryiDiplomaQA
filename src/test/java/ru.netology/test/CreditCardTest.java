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

public class CreditCardTest {

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

    @Test // Тест - ок/ нужно менять строку с БД
    @DisplayName("2. Покупка по одобренной кредитной карте (Статус Approved)")
    void shouldPayByAppDC() {
       // System.setProperty("url", "jdbc:postgresql://localhost:5432/app"); // url - ключ (имя переменной), "jdbc..../app"- значение переменной
        System.setProperty("url", "jdbc:mysql://localhost:3306/app");
        val creditCardPage = dashboardPage.payByCreditCard();
        val approvedCardInformation = DataHelper.getApprovedCardInfo();
        //System.out.println(approvedCardInformation.);
        creditCardPage.cardInfo(approvedCardInformation);
        creditCardPage.okNotification();
        val paymentStatus = SqlHelper.getCreditEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test // тест упал //Форма выдает сообщение об успешной оплате по дебитовой/кредитной карте со статусом Declined -Баг
    @DisplayName("4. Покупка по отклоненной кредитной карте (Статус Declined)")
    void shouldPayNotByDecDC() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInfo();
        creditCardPage.cardInfo(declinedCardInformation);
        creditCardPage.nokNotification();
        val paymentStatus = SqlHelper.getCreditEntity();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test // Тест прошел -Ок
    @DisplayName("6. Покупка по кредитной карте с невалидным номером")
    void shouldNotPayByInvNum() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val invalidCardInformation = DataHelper.getInvalidCardInfo();
        creditCardPage.cardInfo(invalidCardInformation);
        creditCardPage.messInvalidCardNumber();
    }

    @Test // Тест прошел -Ок
    @DisplayName("8. Покупка по кредитной карте с неполным номером")
    void shouldErrorNotFullNum() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val notFullCardInformation = DataHelper.getNotFullCardInfo();
        creditCardPage.cardInfo(notFullCardInformation);
        creditCardPage.messErrorNum();
    }

    @Test // Тест прошел -Ок
    @DisplayName("10. Покупка по кредитной карте с указанием невалидного месяца")
    void shouldErrorInvalidMonth() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val invalidMonthCardInformation = DataHelper.getInvalidMonthCardInfo();
        creditCardPage.cardInfo(invalidMonthCardInformation);
        creditCardPage.messInvalidMonth();
    }

    @Test // Тест прошел -Ок
    @DisplayName("12. Покупка по кредитной карте с указанием истекшего месяца")
    void shouldErrorExpiredMonth() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val expiredMonthCardInformation = DataHelper.getExpiredMonthCardInfo();
        creditCardPage.cardInfo(expiredMonthCardInformation);
        creditCardPage.messExpiredMonth();
    }

    @Test // Тест прошел -Ок
    @DisplayName("14. Покупка по кредитной карте с указанием истекшего года")
    void shouldErrorExpiredYear() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInfo();
        creditCardPage.cardInfo(expiredYearCardInformation);
        creditCardPage.messExpiredYearField();
    }

    @Test // тест упал ---- /Форма не выдает ошибку при вводе невалидных значений в поле Владелец - Баг
    @DisplayName("16. Покупка по кредитной карте с указанием невалидных значений в поле Владелец")
    void shouldErrorInvalidOwner() {
        val creditCardPage = dashboardPage.payByCreditCard();;
        val invalidOwner = DataHelper.getInvalidOwnerCard();
        creditCardPage.cardInfo(invalidOwner);
        creditCardPage.messInvalidOwner();
    }

    @Test // Тест прошел -Ок
    @DisplayName("18. Покупка по кредитной карте с неполным вводом в поле CVC/CVV")
    void shouldErrorCvc() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val invalidCvc = DataHelper.getInvalidCvc();
        creditCardPage.cardInfo(invalidCvc);
        creditCardPage.messInvalidCvc();
    }

    @Test // Тест прошел -Ок
    @DisplayName("20. Пустая форма заявки для покупки по кредитной карте")
    void shouldNotSendEmptyForm() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val emptyForm = DataHelper.getEmptyCardInfo();
        creditCardPage.cardInfo(emptyForm);
        creditCardPage.messEmptyCardNumberField();
        creditCardPage.messEmptyMonthField();
        creditCardPage.messEmptyYearField();
        creditCardPage.messEmptyOwnerField();
        creditCardPage.messEmptyCvcField();
    }

    @Test // Тест прошел -Ок
    @DisplayName("22. Покупка по кредитной карте с пустым полем Номер карты")
    void shouldErrorEmptyCardNum() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val emptyCardNum = DataHelper.getEmptyCardNumber();
        creditCardPage.cardInfo(emptyCardNum);
        creditCardPage.messEmptyCardNumberField();
    }

    @Test // Тест прошел -Ок
    @DisplayName("24. Покупка по кредитной карте с пустым полем Месяц")
    void shouldErrorEmptyMonth() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val emptyMonth = DataHelper.getEmptyMonth();
        creditCardPage.cardInfo(emptyMonth);
        creditCardPage.messEmptyMonthField();
    }
    @Test // Тест прошел -Ок
    @DisplayName("26. Покупка по кредитной карте с пустым полем Год")
    void shouldErrorEmptyYear() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val emptyYear = DataHelper.getEmptyYear();
        creditCardPage.cardInfo(emptyYear);
        creditCardPage.messEmptyYearField();
    }
    @Test // Тест прошел -Ок
    @DisplayName("28. Покупка по кредитной карте с пустым полем Владелец")
    void shouldErrorEmptyOwner() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val emptyOwner = DataHelper.getEmptyOwner();
        creditCardPage.cardInfo(emptyOwner);
        creditCardPage.messEmptyOwnerField();
    }
    @Test // Тест прошел -Ок
    @DisplayName("30. Покупка по кредитной карте с пустым полем Cvc")
    void shouldErrorEmptyCvc() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val emptyCvc = DataHelper.getEmptyCvc();
        creditCardPage.cardInfo(emptyCvc);
        creditCardPage.messEmptyCvcField();
    }
    @Test // тест упал - - Форма не выдает сообщение об ошибке при вводе невалидных значений в поле CVC- Баг
    @DisplayName("32. Покупка по кредитной карте с вводом 000 в поле Cvc")
    void shouldErrorZeroCvc() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val zeroCvc = DataHelper.getZeroCvc();
        creditCardPage.cardInfo(zeroCvc);
        creditCardPage.messInvalidCvc();
    }
    @Test // Тест прошел локально, но не прошел при массовом запуске  - ВОПРОС
    @DisplayName("34. Покупка по кредитной карте с вводом 0 в поле Номер карты")
    void shouldErrorZeroCardNum() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val zeroCardNum = DataHelper.getCardZeroNumber();
        creditCardPage.cardInfo(zeroCardNum);
        creditCardPage.messZeroNum();
    }
    @Test // Тест прошел -Ок
    @DisplayName("36. Покупка по кредитной карте с вводом 0 в поле Месяц")
    void shouldErrorZeroMonth() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val zeroMonth = DataHelper.getZeroMonthCardInfo();
        creditCardPage.cardInfo(zeroMonth);
        creditCardPage.messInvalidMonth();
    }
    @Test // Тест прошел -Ок
    @DisplayName("38. Покупка по кредитной карте с вводом 0 в поле Год")
    void shouldErrorZeroYear() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val zeroYear = DataHelper.getZeroYearCardInformation();
        creditCardPage.cardInfo(zeroYear);
        creditCardPage.messInvalidYear();
    }
}
