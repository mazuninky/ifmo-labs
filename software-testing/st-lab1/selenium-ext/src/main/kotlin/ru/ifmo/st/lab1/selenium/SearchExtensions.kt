package ru.ifmo.st.lab1.selenium

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

fun SearchContext.bySelector(selector: String, body: WebElement.() -> Unit): WebElement {
    return findElement(By.cssSelector(selector)).apply(body)
}

fun SearchContext.elementExists(by: By) = findElements(by).isNotEmpty()

fun SearchContext.elementBySelectorExists(selector: String) = findElements(By.cssSelector(selector)).isNotEmpty()