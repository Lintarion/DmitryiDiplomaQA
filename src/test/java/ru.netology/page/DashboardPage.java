package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private final SelenideElement payButton = $$("button").find(exactText("Купить"));
    private final SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));


    public DebitCardPage payByDebitCard() {
        payButton.click();
        return new DebitCardPage();
    }

    public CreditCardPage payByCreditCard() {
        creditButton.click();
        return new CreditCardPage();
    }
}
