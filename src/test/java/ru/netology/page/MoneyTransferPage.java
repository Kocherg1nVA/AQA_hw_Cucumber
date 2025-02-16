package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {

    private static final SelenideElement heading = $("h1.heading");
    private static final SelenideElement amountField = $("[data-test-id='amount'] input");
    private static final SelenideElement cardFromField = $("[data-test-id='from'] input");
    private static final SelenideElement addButton = $("[data-test-id='action-transfer'].button");
    private static final SelenideElement disabledField = $("[data-test-id='to'] input.input__control");
    private static final SelenideElement errMsg = $("[data-test-id='error-notification'] _notification__content");

    public MoneyTransferPage() {
        heading.shouldBe(Condition.visible);
    }

    public DashboardPage moneyTransfer(String amount, String cardNumber) {
        amountField.setValue(amount);
        cardFromField.setValue(String.valueOf(cardNumber));
        addButton.click();
        return new DashboardPage();
    }


    public void amountMoreThanBalance() {
        errMsg.shouldHave(Condition.exactText("Ошибка! Недостаточно средств на счёте.")).shouldBe(Condition.visible);
    }
}
