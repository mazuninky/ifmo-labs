package ru.ifmo.st.lab1.app.page

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class MenuPopup {
    @FindBy(css = "a[href$='#setup/sender']")
    lateinit var sender: WebElement

    @FindBy(css = "a[href$='#setup/collectors']")
    lateinit var collectors: WebElement

    @FindBy(css = "a[href$='#setup/folders']")
    lateinit var folders: WebElement

    @FindBy(css = "a[href$='#setup/filters']")
    lateinit var filters: WebElement

    @FindBy(css = "a[href$='#setup/security']")
    lateinit var security: WebElement

    @FindBy(css = "a[href$='#setup/abook']")
    lateinit var contacts: WebElement

    @FindBy(css = "a[href$='#setup/todo']")
    lateinit var todo: WebElement
}
