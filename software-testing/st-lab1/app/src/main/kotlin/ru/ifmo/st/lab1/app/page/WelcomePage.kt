package ru.ifmo.st.lab1.app.page

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class WelcomePage : WebPage() {
    override val url: String = welcomePageUrl

    @FindBy(css = ".HeadBanner-ButtonsWrapper a.button2:first-child")
    lateinit var signUpButton: WebElement
        private set

    @FindBy(css = ".HeadBanner-ButtonsWrapper a.button2:last-child")
    lateinit var loginButton: WebElement
        private set
}
