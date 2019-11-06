package ru.ifmo.st.lab1.app.test

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import ru.ifmo.st.lab1.app.data.defaultUser
import ru.ifmo.st.lab1.app.page.*
import ru.ifmo.st.lab1.dsl.Step
import ru.ifmo.st.lab1.dsl.TestCaseSuite
import ru.ifmo.st.lab1.selenium.*

class AuthPageTest : TestCaseSuite({
    Test("Auth email test") {
        val page: AuthPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter inavlid email") {
            page.apply {
                loginField.sendKeys("asd@tusion.xyz")
            }
        }

        Step("Click to login button") {
            page.apply {
                loginBtn.click()
            }
        }

        Step("Assert error") {
            assert(driver.findElement(By.className("passp-form-field_failed")).isDisplayed)
        }
    }

    Test("Auth password test") {
        val page: AuthPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter email") {
            page.apply {
                loginField.sendKeys(defaultUser.login)
            }
        }

        Step("Click to login button") {
            page.apply {
                loginBtn.click()
            }
        }

        Step("Enter incorrect password") {
            page.apply {
                passwordField.sendKeys("12345")
            }
        }

        Step("Click to enter button") {
            page.apply {
                enterBtn.click()
            }
        }

        Step("Assert error") {
            assert(driver.findElement(By.className("passp-form-field_failed")).isDisplayed)
        }
    }

    Test("Go to registration") {
        val page: AuthPage
        val wait: WebDriverWait

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
            wait = driver.createWait()
        }

        Step("Go to registration") {
            page.apply {
                registrationBtn.click()
            }
        }

        Step("Assert url") {
            wait.waitUrl(registrationPageUrl)
            assert(driver.currentUrl.startsWith(registrationPageUrl))
        }
    }

    Test("Auth page test") {
        val page: AuthPage
        val wait: WebDriverWait

        Step("load page") {
            page = driver.initPage()
            wait = driver.createWait()
            driver.open(page)
        }

        Step("Enter email") {
            page.apply {
                loginField.sendKeys("konstantin@tusion.xyz")
                loginBtn.click()
            }
        }

        Step("Enter password") {
            page.apply {
                passwordField.sendKeys(System.getenv("EMAIL_PASSWORD"))
                enterBtn.click()
            }

            wait.waitUrl(mailBoxUrl)
            assert(driver.currentUrl.startsWith(mailBoxUrl))
        }
    }
})
