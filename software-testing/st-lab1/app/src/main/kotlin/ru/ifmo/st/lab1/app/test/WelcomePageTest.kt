package ru.ifmo.st.lab1.app.test

import org.openqa.selenium.support.ui.WebDriverWait
import ru.ifmo.st.lab1.app.page.*
import ru.ifmo.st.lab1.dsl.Step
import ru.ifmo.st.lab1.dsl.TestCaseSuite
import ru.ifmo.st.lab1.selenium.initPage
import ru.ifmo.st.lab1.selenium.waitUrl
import ru.ifmo.st.lab1.selenium.createWait

class WelcomePageTest : TestCaseSuite({
    Test("Lookup test") {
        val page: WelcomePage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
            driver.createWait().waitUrl(welcomePageUrl)
        }

        Step("Go to auth page") {
            page.apply {
                assert(loginButton.isDisplayed && signUpButton.isDisplayed)
            }
        }

    }

    Test("Welcome page test") {
        val page: WelcomePage
        val wait: WebDriverWait

        Step("load page") {
            page = driver.initPage()
            wait = driver.createWait()
        }

        Step("Go to auth page") {
            driver.open(page)
            driver.createWait().waitUrl(welcomePageUrl)
            page.apply {
                loginButton.click()
            }
            wait.waitUrl(authPageUrl)
            assert(driver.currentUrl.startsWith(authPageUrl))
        }

        Step("Go to registration page") {
            driver.open(page)
            driver.createWait().waitUrl(welcomePageUrl)
            page.apply {
                signUpButton.click()
            }
            wait.waitUrl(registrationPageUrl)
            assert(driver.currentUrl.startsWith(registrationPageUrl))
        }
    }

})
