package ru.ifmo.st.lab1.selenium

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import kotlin.reflect.full.createInstance

inline fun <reified T : Any> WebDriver.initPage(): T {
    val page = T::class.createInstance()
    PageFactory.initElements(this, page)
    return page
}
