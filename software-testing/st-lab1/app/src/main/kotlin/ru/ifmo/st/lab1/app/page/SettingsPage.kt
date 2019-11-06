package ru.ifmo.st.lab1.app.page

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class SettingsPage : WebPage() {
    override val url = settingsUrl

    @FindBy(css = "a[href$='#setup/sender']")
    lateinit var sender: WebElement

    @FindBy(css = ".b-folders a[href$='#setup/sender']")
    lateinit var senderLeft: WebElement

    @FindBy(css = "a[href$='#setup/collectors']")
    lateinit var collectors: WebElement

    @FindBy(css = ".b-folders a[href$='#setup/collectors']")
    lateinit var collectorsLeft: WebElement

    @FindBy(css = "a[href$='#setup/folders']")
    lateinit var folders: WebElement

    @FindBy(css = ".b-folders a[href$='#setup/folders']")
    lateinit var foldersLeft: WebElement

    @FindBy(css = "a[href$='#setup/filters']")
    lateinit var filters: WebElement

    @FindBy(css = ".b-folders a[href$='#setup/filters']")
    lateinit var filtersLeft: WebElement

    @FindBy(css = "a[href$='#setup/security']")
    lateinit var security: WebElement

    @FindBy(css = ".b-folders a[href$='#setup/security']")
    lateinit var securityLeft: WebElement

    @FindBy(css = "a[href$='#setup/abook']")
    lateinit var contacts: WebElement

    @FindBy(css = ".b-folders a[href$='#setup/abook']")
    lateinit var contactsLeft: WebElement

    @FindBy(css = "a[href$='#setup/todo']")
    lateinit var todo: WebElement

    @FindBy(css = ".b-folders a[href$='#setup/todo']")
    lateinit var todoLeft: WebElement
}
