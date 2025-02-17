package ru.netology.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.MoneyTransferPage;
import ru.netology.page.VerificationPage;

public class TemplateSteps {

    private static LoginPage loginPage;
    private static VerificationPage verificationPage;
    private static DashboardPage dashboardPage;
    private static MoneyTransferPage moneyTransferPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void loginPage(String login, String password) {
        Configuration.browser = "firefox";
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        verificationPage = loginPage.validLogin(login, password);
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode());
    }

    @Когда("пользователь переводит {string} рублей с карты номер {string} на свою {int} карту с главной страницы")
    public void moneyTransfer(String amount, String cardFrom, int index) {
        moneyTransferPage = dashboardPage.addToCard(index);
        moneyTransferPage.moneyTransfer(amount, cardFrom);
    }

    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void checkBalance(int index, int actual) {
        Assertions.assertEquals(dashboardPage.getCardBalance(index), actual);
    }
}
