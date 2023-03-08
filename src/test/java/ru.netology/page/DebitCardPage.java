package ru.netology.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitCardPage {

    private final SelenideElement payByCard = $$("h3").find(text("Оплата по карте"));
    private SelenideElement fieldNumber = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement fieldMonth = $("[placeholder='08']");
    private SelenideElement fieldYear = $("[placeholder='22']");
    private SelenideElement fieldOwner = $(byText("Владелец")).parent().$(".input__control");;
    private SelenideElement fieldCVC = $("[placeholder='999']");
    private SelenideElement continueButton = $$(".button").find(exactText("Продолжить"));

    private SelenideElement okNotification = $(withText("Успешно"));
    private SelenideElement nokNotification = $(withText("Ошибка"));

    private SelenideElement fieldNumberError = Selenide.$x("//span[text()='Номер карты']" + "/following-sibling::span[@class='input__sub']");
    private SelenideElement fieldMonthError = Selenide.$x("//span[text()='Месяц']" + "/following-sibling::span[@class='input__sub']");
    private SelenideElement fieldYearError = Selenide.$x("//span[text()='Год']" + "/following-sibling::span[@class='input__sub']");
    private SelenideElement fieldOwnerError = Selenide.$x("//span[text()='Владелец']" + "/following-sibling::span[@class='input__sub']");
    private SelenideElement fieldCvcError = Selenide.$x("//span[text()='CVC/CVV']" + "/following-sibling::span[@class='input__sub']");
    public DebitCardPage() {
        payByCard.shouldBe(visible);
    }
    public void cardInfo(DataHelper.CardInfo cardInfo) {
        fieldNumber.setValue(cardInfo.getCardNumber());
        fieldMonth.setValue(cardInfo.getMonth());
        fieldYear.setValue(cardInfo.getYear());
        fieldOwner.setValue(cardInfo.getOwner());
        fieldCVC.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    public void okNotification() {
        okNotification.waitUntil(visible, 15000);
    }

    public void nokNotification() {nokNotification.waitUntil(visible, 20000);
    }

    public void messInvalidCardNumber() {
        nokNotification.waitUntil(visible, 20000);
    }

    public void messErrorNum() {
        fieldNumberError.shouldHave(text("Неверный формат")); fieldNumberError.shouldBe(visible);
    }
    public void messZeroNum() {
        fieldNumberError.shouldHave(text("Неверный формат")); fieldNumberError.shouldBe(visible);
    }

    public void messInvalidMonth() {
        fieldMonthError.shouldHave(text("Неверно указан срок действия карты")); fieldMonthError.shouldBe(visible);
    }

    public void messInvalidYear() {
        fieldYearError.shouldHave(text("Истёк срок действия карты")); fieldYearError.shouldBe(visible);
    }

    public void messInvalidOwner() {
        fieldOwnerError.shouldHave(text("Неверный формат")); fieldOwnerError.shouldBe(visible);
    }

    public void messInvalidCvc() {
        fieldCvcError.shouldHave(text("Неверный формат")); fieldCvcError.shouldBe(visible);
    }

    public void messEmptyCardNumberField() {
        fieldNumberError.shouldHave(text("Поле обязательно для заполнения")); fieldNumberError.shouldBe(visible);
    }

    public void messEmptyMonthField() {
        fieldMonthError.shouldHave(text("Поле обязательно для заполнения")); fieldMonthError.shouldBe(visible);
    }

    public void messEmptyYearField() {
        fieldYearError.shouldHave(text("Поле обязательно для заполнения")); fieldYearError.shouldBe(visible);
    }

    public void messEmptyOwnerField() {
        fieldOwnerError.shouldHave(text("Поле обязательно для заполнения")); fieldOwnerError.shouldBe(visible);
    }

    public void messEmptyCvcField() {
        fieldCvcError.shouldHave(text("Поле обязательно для заполнения")); fieldCvcError.shouldBe(visible);
    }

    public void messExpiredYearField() {
        fieldYearError.shouldHave(text("Истёк срок действия карты")); fieldYearError.shouldBe(visible);
    }

    public void messExpiredMonth() {
        fieldMonthError.shouldHave(text("Неверно указан срок действия карты")); fieldMonthError.shouldBe(visible);
    }
}
