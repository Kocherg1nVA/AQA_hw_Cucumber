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

import static ru.netology.page.DashboardPage.addToFirstCard;
import static ru.netology.page.LoginPage.validLogin;
import static ru.netology.page.MoneyTransferPage.moneyTransfer;
import static ru.netology.page.VerificationPage.validVerify;

public class TemplateSteps {

    private static LoginPage loginPage;
    private static VerificationPage verificationPage;
    private static DashboardPage dashboardPage;
    private static MoneyTransferPage moneyTransferPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void LoginPage(String login, String password) {
        Configuration.browser = "firefox";
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        verificationPage = validLogin(login, password);
        dashboardPage = validVerify(DataHelper.getVerificationCode());
    }
    @Когда("пользователь переводит {string} рублей с карты с номером 5559 0000 0000 0002 на свою 1 карту с главной страницы")
    public void DashboardPage(String amount){
        moneyTransferPage = addToFirstCard();
        moneyTransfer(amount);
    }
    @Тогда("баланс его 1 карты из списка на главной странице должен стать {int} рублей")
    public void checkBalanceFirstCard(int actual){
        var cardNumber = DataHelper.getFirstCard().getNumber();
        var expected = DashboardPage.getCardBalance(cardNumber);
        Assertions.assertEquals(expected, actual);
    }
}
