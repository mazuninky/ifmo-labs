package ru.ifmo.st.lab1.app.test

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import ru.ifmo.st.lab1.app.data.generateRandomString
import ru.ifmo.st.lab1.app.data.randomAlph
import ru.ifmo.st.lab1.app.page.*
import ru.ifmo.st.lab1.app.userAuth
import ru.ifmo.st.lab1.dsl.Step
import ru.ifmo.st.lab1.dsl.TestCaseSuite
import ru.ifmo.st.lab1.selenium.*

class SettingsPageTest : TestCaseSuite({
    Test("User auth precondition") {
        Step("User must be auth") {
            driver.userAuth()
        }
    }

    Test("Open sender") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open data about sender") {
            page.sender.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/sender"))
        }
    }

    Test("Open sender") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open data about sender") {
            page.senderLeft.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/sender"))
        }
    }

    Test("Open collectors") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open collectors") {
            page.collectors.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/collectors"))
        }
    }

    Test("Open collectors") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open collectors") {
            page.collectorsLeft.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/collectors"))
        }
    }

    Test("Open folders settings") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open folders settings") {
            page.folders.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/folders"))
        }
    }

    Test("Open folders settings") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open folders settings") {
            page.foldersLeft.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/folders"))
        }
    }


    Test("Open filters") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open filters") {
            page.filters.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/filters"))
        }
    }

    Test("Open filters left") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open filters") {
            page.filtersLeft.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/filters"))
        }
    }


    Test("Open security") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open security") {
            page.security.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/security"))
        }
    }


    Test("Open security") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open security") {
            page.securityLeft.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/security"))
        }
    }

    Test("Open contacts") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open contacts") {
            page.contacts.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/abook"))
        }
    }

    Test("Open contacts") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open contacts") {
            page.contactsLeft.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/abook"))
        }
    }

    Test("Open todo") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open todo") {
            page.todo.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/todo"))
        }
    }


    Test("Open todo") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("open todo") {
            page.todoLeft.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/todo"))
        }
    }

    Test("Lookup") {
        val page: SettingsPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Lookup") {
            page.apply {
                assert(sender.isDisplayed && senderLeft.isDisplayed &&
                        collectors.isDisplayed && collectorsLeft.isDisplayed &&
                        folders.isDisplayed && foldersLeft.isDisplayed &&
                        filters.isDisplayed && filtersLeft.isDisplayed &&
                        security.isDisplayed && securityLeft.isDisplayed &&
                        contacts.isDisplayed && contactsLeft.isDisplayed &&
                        todo.isDisplayed && todoLeft.isDisplayed
                )
            }
        }
    }

})
