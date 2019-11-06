package ru.ifmo.st.lab1.app.test

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import ru.ifmo.st.lab1.app.createEmail
import ru.ifmo.st.lab1.app.page.*
import ru.ifmo.st.lab1.app.userAuth
import ru.ifmo.st.lab1.dsl.Step
import ru.ifmo.st.lab1.dsl.TestCaseSuite
import ru.ifmo.st.lab1.selenium.*

class CreateMailPageTest : TestCaseSuite({
    Test("User auth precondition") {
        Step("User must be auth") {
            driver.userAuth()
        }
    }

    Test("Send email") {
        val page: CreateEmailPage

        Step("open box") {
            val boxPage: MailBoxPage = driver.initPage()
            driver.open(boxPage)
            boxPage.createEmailBtn.click()
            driver.createWait().waitUrl("#compose")
        }

        Step("init page") {
            page = driver.initPage()
        }

        Step("enter subject") {
            page.subject.sendKeys("Test")
        }

        Step("enter email") {
            page.to.sendKeys("mazuninky@gmail.com")
        }

        Step("enter text") {
            page.text.sendKeys("dasdasdsadsasda")
        }

        Step("Send email") {
            page.sendBtn.click()
        }

        Step("Check url") {
            driver.createWait().waitUrl("#done")
            assert(driver.currentUrl.endsWith("#done"))
        }
    }

    Test("Delete email") {
        val page: CreateEmailPage

        Step("open box") {
            val boxPage: MailBoxPage = driver.initPage()
            driver.open(boxPage)
            boxPage.createEmailBtn.click()
            driver.createWait().waitUrl("#compose")
        }

        Step("init page") {
            page = driver.initPage()
        }

        Step("click delete") {
            page.deleteBtn.click()
        }

        Step("Check url") {
            driver.createWait().waitUrl("#inbox")
            assert(driver.currentUrl.endsWith("#inbox"))
        }
    }

    Test("Close email") {
        val page: CreateEmailPage

        Step("open box") {
            val boxPage: MailBoxPage = driver.initPage()
            driver.open(boxPage)
            driver.createWait().waitUrl("#inbox")
            boxPage.createEmailBtn.click()
            driver.createWait().waitUrl("#compose")
        }

        Step("init page") {
            page = driver.initPage()
        }

        Step("click delete") {
            page.closeBtn.click()
        }

        Step("Check url") {
            driver.createWait().waitUrl("#inbox")
            assert(driver.currentUrl.endsWith("#inbox"))
        }
    }

    Test("Write bold text") {
        val page: CreateEmailPage

        Step("open box") {
            val boxPage: MailBoxPage = driver.initPage()
            driver.open(boxPage)
            driver.createWait().waitUrl("#inbox")
            boxPage.createEmailBtn.click()
            driver.createWait().waitUrl("#compose")
        }

        Step("init page") {
            page = driver.initPage()
        }

        Step("Set on bold") {
            page.boldText.click()
        }

        Step("Write bold text") {
           page.text.sendKeys("lol")
        }

        Step("Set on off") {
            page.boldText.click()
        }

        Step("Assert text is bold") {
            assert(page.innerText.findElement(By.cssSelector("strong")).isDisplayed)
            page.createEmailBtn.click()
            page.dontSave.click()
        }
    }

    Test("Write italic text") {
        val page: CreateEmailPage

        Step("open box") {
            val boxPage: MailBoxPage = driver.initPage()
            driver.open(boxPage)
            driver.createWait().waitUrl("#inbox")
            boxPage.createEmailBtn.click()
            driver.createWait().waitUrl("#compose")
        }

        Step("init page") {
            page = driver.initPage()
        }

        Step("Set on italic") {
            page.italicText.click()
        }

        Step("Write italic text") {
            page.text.sendKeys("lol")
        }

        Step("Set off") {
            page.italicText.click()
        }

        Step("Assert text is italic") {
            assert(page.innerText.findElement(By.cssSelector("em")).isDisplayed)
            page.createEmailBtn.click()
            page.dontSave.click()
        }
    }

    Test("Write underline text") {
        val page: CreateEmailPage

        Step("open box") {
            val boxPage: MailBoxPage = driver.initPage()
            driver.open(boxPage)
            driver.createWait().waitUrl("#inbox")
            boxPage.createEmailBtn.click()
            driver.createWait().waitUrl("#compose")
        }

        Step("init page") {
            page = driver.initPage()
        }

        Step("Set on underline") {
            page.underlineText.click()
        }

        Step("Write underline text") {
            page.text.sendKeys("lol")
        }

        Step("Set off") {
            page.underlineText.click()
        }

        Step("Assert text is underline") {
            assert(page.innerText.findElement(By.cssSelector("u")).isDisplayed)
            page.createEmailBtn.click()
            page.dontSave.click()
        }
    }
})
