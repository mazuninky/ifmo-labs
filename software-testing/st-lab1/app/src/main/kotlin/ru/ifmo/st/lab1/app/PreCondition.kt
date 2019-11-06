package ru.ifmo.st.lab1.app

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import ru.ifmo.st.lab1.app.data.defaultUser
import ru.ifmo.st.lab1.app.page.*
import ru.ifmo.st.lab1.selenium.*
import java.util.concurrent.TimeUnit

fun WebDriver.userAuth() {
    get(authPageUrl)
    val authPage = initPage<AuthPage>()
    timeout(5, TimeUnit.SECONDS)
    if (findElements(By.xpath("/html/body/div[1]/div/div/div[2]/div/div[2]/div[3]/div[2]/div/div/ul/li/div/a[1]")).isEmpty()) {
        authPage.login(defaultUser)
        createWait().waitUrl(yandexPassportPageUrl)
    }
    timeout(30, TimeUnit.SECONDS)
}


fun WebDriver.createEmail() {
    val emailPage = initPage<MailBoxPage>()
    open(emailPage)
    createWait().waitVisible( emailPage.createEmailBtn)
    emailPage.createEmailBtn.click()
    createWait().waitUrl("#compose")
}
