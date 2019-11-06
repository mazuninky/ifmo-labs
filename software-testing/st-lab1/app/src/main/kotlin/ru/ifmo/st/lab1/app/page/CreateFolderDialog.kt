package ru.ifmo.st.lab1.app.page

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class CreateFolderDialog {

    @FindBy(xpath = "//*[@id=\"nb-4\"]")
    lateinit var folderName: WebElement

    @FindBy(xpath = "/html/body/div[8]/div[2]/table/tbody/tr/td/div[4]/button[1]/span/span")
    lateinit var createFolderNameBtn: WebElement
}
