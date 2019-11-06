package ru.ifmo.st.lab1.app.page

import org.openqa.selenium.WebDriver

abstract class WebPage {
    abstract val url: String
}

fun WebDriver.open(page: WebPage) {
    get(page.url)
}
