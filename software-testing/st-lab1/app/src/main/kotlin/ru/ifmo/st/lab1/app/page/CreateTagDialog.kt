package ru.ifmo.st.lab1.app.page

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class CreateTagDialog {

    @FindBy(css = ".b-form-element input")
    lateinit var tagField: WebElement

    @FindBy(css = ".b-popup__confirm button")
    lateinit var createTagBtn: WebElement

}
