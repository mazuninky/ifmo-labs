package ru.ifmo.st.lab3

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

fun <T : MobileElement> AndroidDriver<T>.wait(element: MobileElement) {
    val wait = WebDriverWait(this, 1000)
    wait.until(ExpectedConditions.visibilityOf(element))
}


fun <T : MobileElement> AndroidDriver<T>.waitGone(element: MobileElement) {
    val wait = WebDriverWait(this, 1000)
    wait.until(ExpectedConditions.invisibilityOf(element))
}
