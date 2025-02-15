package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private static final SelenideElement loginField = $("[data-test-id='login'] input");
    private static final SelenideElement passwordField = $("[data-test-id='password'] input");
    private static final SelenideElement actionLoginButton = $("[data-test-id='action-login']");

    public static VerificationPage validLogin(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        actionLoginButton.click();
        return new VerificationPage();
    }
}
