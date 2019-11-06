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

class MailBoxPageTest : TestCaseSuite({
    Test("User auth precondition") {
        Step("User must be auth") {
            driver.userAuth()
        }
    }

    Test("Open email") {
        val page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        val messages: List<Email>
        Step("assert box not empty") {
            messages = page.messages
            assert(messages.isNotEmpty())
        }

        val subject: String
        Step("Open first email") {
            val email = messages.first()
            email.click()

            subject = email.message
        }

        Step("Check subject") {
            val messagePage = driver.initPage<MessagePage>()
            assert(messagePage.subject.text == subject)
        }
    }

    Test("Open inbox") {
        val page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("init page") {
            page.apply {
                inboxBtn.click()
                driver.createWait().waitUrl("#inbox")
                assert(driver.currentUrl.endsWith("#inbox"))
            }
        }
    }

    Test("Open sent box") {
        val page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("init page") {
            page.apply {
                sendBox.click()
                driver.createWait().waitUrl("#sent")
                assert(driver.currentUrl.endsWith("#sent"))
            }
        }
    }

    Test("Open spam box") {
        val page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("init page") {
            page.apply {
                spamBox.click()
                driver.createWait().waitUrl("#spam")
                assert(driver.currentUrl.endsWith("#spam"))
            }
        }
    }

    Test("Open draft box") {
        val page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("init page") {
            page.apply {
                draftBox.click()
                driver.createWait().waitUrl("#draft")
                assert(driver.currentUrl.endsWith("#draft"))
            }
        }
    }

    Test("Delete email") {
        val page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Select first email") {
            page.select(0)
        }

        Step("Delete email") {
            page.toolbarDelete.click()
        }
    }

    Test("Read email") {
        var page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }
        Step("Select email") {
            page.select(0)
        }

        Step("Read email") {
            page.toolbarRead.click()
        }
    }

    Test("Move to spam") {
        val page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Select first email") {
            page.select(0)
        }

        Step("Move to spam") {
            page.toolbarSpam.click()
        }
    }

    Test("Create email") {
        val page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Create email") {
            page.createEmailBtn.click()
        }

        Step("Check url") {
            assert(driver.currentUrl.endsWith("#compose"))
        }
    }

    Test("Create folder") {
        val page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }


        val dialog: CreateFolderDialog
        Step("Open create tag dialog") {
            page.createFolderBtn.click()
            dialog = driver.initPage()
        }


        Step("Enter name") {
            dialog.folderName.sendKeys(randomAlph.generateRandomString(5))
        }

        Step("create folder") {
            dialog.createFolderNameBtn.click()
        }
    }

    Test("Create tag") {
        val page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }


        val dialog: CreateTagDialog
        Step("Open create tag dialog") {
            page.createTagBtn.click()
            dialog = driver.initPage()
        }


        Step("Enter name") {
            dialog.tagField.sendKeys(randomAlph.generateRandomString(5))
        }

        Step("create tag") {
            dialog.createTagBtn.click()
        }
    }

    Test("Open menu personal data") {
        var page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }


        val popup: MenuPopup
        Step("Open menu popup") {
            page.settingsBtn.click()
            popup = driver.initPage()
        }


        Step("Open personal data") {
            popup.sender.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/sender"))
        }
    }

    Test("Open menu collectors") {
        var page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }


        val popup: MenuPopup
        Step("Open menu popup") {
            page.settingsBtn.click()
            popup = driver.initPage()
        }

        Step("Open collectors") {
            popup.collectors.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/collectors"))
        }
    }

    Test("Open menu folders") {
        var page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        val popup: MenuPopup
        Step("Open menu popup") {
            page.settingsBtn.click()
            popup = driver.initPage()
        }

        Step("Open folders") {
            popup.folders.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/folders"))
        }
    }

    Test("Open filters") {
        var page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        val popup: MenuPopup
        Step("Open menu popup") {
            page.settingsBtn.click()
            popup = driver.initPage()
        }

        Step("Open folders") {
            popup.filters.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/filters"))
        }
    }

    Test("Open filters") {
        var page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        val popup: MenuPopup
        Step("Open menu popup") {
            page.settingsBtn.click()
            popup = driver.initPage()
        }

        Step("Open security") {
            popup.security.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/security"))
        }
    }

    Test("Open contacts") {
        var page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        val popup: MenuPopup
        Step("Open menu popup") {
            page.settingsBtn.click()
            popup = driver.initPage()
        }

        Step("Open contacts") {
            popup.contacts.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/abook"))
        }
    }

    Test("Open todo") {
        var page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        val popup: MenuPopup
        Step("Open menu popup") {
            page.settingsBtn.click()
            popup = driver.initPage()
        }

        Step("Open todo") {
            popup.todo.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#setup/todo"))
        }
    }

    Test("Open contacts") {
        var page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
        }

        Step("Open contacts") {
            page.contactsBtn.click()
        }

        Step("Assert url") {
            assert(driver.currentUrl.endsWith("#contacts"))
        }
    }

    Test("Lookup test") {
        var page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
            driver.createWait().waitUrl(mailBoxUrl)
        }

        Step("Lookup") {
            page.apply {
                assert(inboxBtn.isDisplayed && sendBox.isDisplayed &&
                        spamBox.isDisplayed && createFolderBtn.isDisplayed &&
                        settingsBtn.isDisplayed
                )
            }
        }
    }

    Test("Create folder dialog lookup test") {
        var page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
            driver.createWait().waitUrl(mailBoxUrl)
        }

        val dialog: CreateFolderDialog
        Step("Open create directory dialog") {
            page.createFolderBtn.click()
            dialog = driver.initPage()
        }

        Step("Lookup") {
            dialog.apply {
                assert(folderName.isDisplayed && createFolderNameBtn.isDisplayed)
            }
        }
    }


    Test("Create tag dialog lookup test") {
        var page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
            driver.createWait().waitUrl(mailBoxUrl)
        }

        val dialog: CreateTagDialog
        Step("Open create tag dialog") {
            page.createTagBtn.click()
            dialog = driver.initPage()
        }

        Step("Lookup") {
            dialog.apply {
                assert(tagField.isDisplayed && createTagBtn.isDisplayed)
            }
        }
    }


    Test("Settings dialog lookup test") {
        var page: MailBoxPage

        Step("init page") {
            page = driver.initPage()
            driver.open(page)
            driver.createWait().waitUrl(mailBoxUrl)
        }

        val popup: MenuPopup
        Step("Open menu popup") {
            page.settingsBtn.click()
            popup = driver.initPage()
        }


        Step("Lookup") {
            popup.apply {
                assert(sender.isDisplayed && collectors.isDisplayed &&
                        folders.isDisplayed && filters.isDisplayed &&
                        security.isDisplayed && contacts.isDisplayed &&
                        todo.isDisplayed
                )
            }
        }
    }
})
