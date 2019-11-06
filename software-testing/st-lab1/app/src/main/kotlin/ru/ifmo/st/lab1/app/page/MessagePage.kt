package ru.ifmo.st.lab1.app.page

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class MessagePage {
    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[5]/div[1]/div/div[2]/div[1]/div[1]/div/div/span")
    lateinit var subject: WebElement
}
