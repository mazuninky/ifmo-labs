package ru.ifmo.st.lab1.app.test

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait
import ru.ifmo.st.lab1.app.data.generateRandomString
import ru.ifmo.st.lab1.app.data.randomAlph
import ru.ifmo.st.lab1.app.page.*
import ru.ifmo.st.lab1.dsl.Step
import ru.ifmo.st.lab1.dsl.TestCaseSuite
import ru.ifmo.st.lab1.selenium.initPage
import ru.ifmo.st.lab1.selenium.createWait
import kotlin.math.log

class RegistrationPageTest : TestCaseSuite({
    Test("Name validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter empty name") {
            page.apply {
                name.sendKeys(" ")
                surname.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__error")).isDisplayed)
        }
    }

    Test("Name validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter long name") {
            page.apply {
                name.sendKeys(randomAlph.generateRandomString(50))
                surname.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__error")).isDisplayed)
        }
    }

    Test("Name validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter normal name") {
            page.apply {
                name.sendKeys(randomAlph.generateRandomString(5))
                surname.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__valid")).isDisplayed)
        }
    }

    Test("Surname validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter empty surname") {
            page.apply {
                surname.sendKeys(" ")
                name.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__error")).isDisplayed)
        }
    }

    Test("Surname validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter long surname") {
            page.apply {
                surname.sendKeys(randomAlph.generateRandomString(50))
                name.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__error")).isDisplayed)
        }
    }

    Test("Surname validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter normal surname") {
            page.apply {
                surname.sendKeys(randomAlph.generateRandomString(5))
                name.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__valid")).isDisplayed)
        }
    }

    Test("Login validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter empty login") {
            page.apply {
                login.sendKeys(" ")
                name.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__error")).isDisplayed)
        }
    }

    Test("Login validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter exists login") {
            page.apply {
                login.sendKeys("a")
                name.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__error")).isDisplayed)
        }
    }

    Test("Login validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter long") {
            page.apply {
                login.sendKeys(randomAlph.generateRandomString(50))
                name.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__error")).isDisplayed)
        }
    }

    Test("Login validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter normal login") {
            page.apply {
                surname.sendKeys("mazuninky${randomAlph.generateRandomString(5)}")
                name.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__valid")).isDisplayed)
        }
    }

    Test("Password validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter empty pass") {
            page.apply {
                password.sendKeys(" ")
                name.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__error")).isDisplayed)
        }
    }

    Test("Password validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter long pass") {
            page.apply {
                password.sendKeys(randomAlph.generateRandomString(120))
                name.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__error")).isDisplayed)
        }
    }

    Test("Password validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter pass") {
            page.apply {
                password.sendKeys(randomAlph.generateRandomString(12))
                name.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__valid")).isDisplayed)
        }
    }

    Test("Password validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter matching password") {
            page.apply {
                val pass = randomAlph.generateRandomString(12)
                password.sendKeys(pass)
                passwordConfirm.sendKeys(pass)
                name.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__valid")).isDisplayed)
        }
    }

    Test("Password validation") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Enter mismatching password") {
            page.apply {
                password.sendKeys(randomAlph.generateRandomString(12))
                passwordConfirm.sendKeys(randomAlph.generateRandomString(10))
                name.sendKeys(" ")
            }

            assert(driver.findElement(By.className("field__error")).isDisplayed)
        }
    }

    Test("Registration test") {
        val page: RegistrationPage
        val wait: WebDriverWait

        Step("load page") {
            page = driver.initPage()
            wait = driver.createWait()
            driver.open(page)
        }

        Step("Enter name") {
            page.apply {
                name.sendKeys("teafsfs")
                surname.sendKeys("etasds")
                login.sendKeys("testkmazunin" + randomAlph.generateRandomString(10))
                val pass = randomAlph.generateRandomString(10)
                password.sendKeys(pass)
                passwordConfirm.sendKeys(pass)
                phoneNumber.sendKeys("800 555 35 35")
                assert(registrationBtn.isEnabled)
            }
        }
    }

    Test("Lookup test") {
        val page: RegistrationPage

        Step("load page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Lookup") {
            page.apply {
                assert(name.isDisplayed && surname.isDisplayed &&
                        login.isDisplayed && password.isDisplayed &&
                        passwordConfirm.isDisplayed && phoneNumber.isDisplayed &&
                        registrationBtn.isDisplayed)
            }
        }
    }

})
