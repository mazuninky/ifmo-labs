package ru.ifmo.st.lab1.selenium

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

fun WebDriver.wait(timeout: Long = 30, sleepTimeout: Long = 500, init: WebDriverWait.() -> Unit) {
    WebDriverWait(this, timeout, sleepTimeout).init()
}

fun WebDriver.createWait(timeout: Long = 30): WebDriverWait {
    return WebDriverWait(this, timeout)
}


fun WebDriverWait.waitUrl(url: String) {
    until(ExpectedConditions.urlContains(url))
}

fun WebDriverWait.waitVisible(element: WebElement) {
    until(ExpectedConditions.visibilityOf(element))
}

fun WebDriverWait.waitInvisible(element: WebElement) {
    until(ExpectedConditions.invisibilityOf(element))
}

