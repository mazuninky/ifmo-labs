package ru.ifmo.st.lab1.app.page

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.CacheLookup
import org.openqa.selenium.support.FindBy

class RegistrationPage : WebPage() {
    override val url: String = registrationPageUrl

    @FindBy(xpath = "//*[@id=\"firstname\"]")
    lateinit var name: WebElement

    @FindBy(xpath = "//*[@id=\"lastname\"]")
    lateinit var surname: WebElement

    @FindBy(xpath = "//*[@id=\"login\"]")
    lateinit var login: WebElement

    @FindBy(xpath = "//*[@id=\"password\"]")
    lateinit var password: WebElement

    @FindBy(xpath = "//*[@id=\"password_confirm\"]")
    lateinit var passwordConfirm: WebElement

    @FindBy(xpath = "//*[@id=\"phone\"]")
    lateinit var phoneNumber: WebElement

    @FindBy(xpath = "/html/body/div[1]/div/div[2]/div/main/div/div/div/form/div[4]/button")
    lateinit var registrationBtn: WebElement
}
