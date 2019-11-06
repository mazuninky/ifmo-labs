package ru.ifmo.st.lab1.app.page

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.CacheLookup
import org.openqa.selenium.support.FindBy
import ru.ifmo.st.lab1.app.data.User

class AuthPage : WebPage() {
    override val url: String = "https://passport.yandex.ru/auth?from=mail&origin=hostroot_homer_auth_ru&retpath=https%3A%2F%2Fmail.yandex.ru%2F%3Fnoretpath%3D1&backpath=https%3A%2F%2Fmail.yandex.ru%3Fnoretpath%3D1"

    @FindBy(xpath = "//*[@id=\"passp-field-login\"]")
    lateinit var loginField: WebElement

    @FindBy(xpath = "//*[@id=\"passp-field-passwd\"]")
    lateinit var passwordField: WebElement

    @FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div/div[2]/div[3]/div[2]/div/div/div[1]/form/div[4]/a")
    lateinit var registrationBtn: WebElement

    @FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div/div[2]/div[3]/div[2]/div/div/div[1]/form/div[3]/button[1]")
    lateinit var loginBtn: WebElement

    @FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div/div[2]/div[3]/div[2]/div/div/form/div[2]/button[1]")
    lateinit var enterBtn: WebElement

    fun login(login: String, password: String) {
        loginField.sendKeys(login)
        loginBtn.click()

        passwordField.sendKeys(password)
        enterBtn.click()
    }

    fun login(user: User) {
        loginField.sendKeys(user.login)
        loginBtn.click()

        passwordField.sendKeys(user.password)
        enterBtn.click()
    }
}
