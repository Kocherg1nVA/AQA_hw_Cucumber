package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private static final SelenideElement heading = $("h1.heading");
    private static final ElementsCollection cards = $$(".list__item");
    private static final ElementsCollection addButtons = $$("button[data-test-id='action-deposit']");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public int getCardBalance(int index) {
        return extractBalance(cards.get(index - 1).getText());
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public MoneyTransferPage addToCard(int index) {
        addButtons.get(index - 1).click();
        return new MoneyTransferPage();
    }
}
