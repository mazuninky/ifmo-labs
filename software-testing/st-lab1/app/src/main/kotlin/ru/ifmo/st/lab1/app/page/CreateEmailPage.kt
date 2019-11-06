package ru.ifmo.st.lab1.app.page

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class CreateEmailPage {
    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[2]/div[2]/div/div/a")
    lateinit var createEmailBtn: WebElement

  @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[5]/div/div[1]/div[1]/div[1]/span[1]/span/span/button/span/span/span")
    lateinit var sendBtn: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[5]/div/div[1]/div[2]/div[1]/div/div[1]/label/div[3]/div")
    lateinit var to: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[5]/div/div[1]/div[2]/div[1]/div/label/div[3]/input")
    lateinit var subject: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[5]/div/div[1]/div[2]/div[2]/label/div[2]/div/div/div/div/div/div[1]")
    lateinit var text: WebElement

    @FindBy(css = ".mail-Compose-Field-Input.mail-Editor-Container")
    lateinit var innerText: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[5]/div/div[1]/div[1]/div[2]/div[1]/svg/rect")
    lateinit var labelBtn: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[5]/div/div[1]/div[1]/div[2]/div[2]")
    lateinit var deleteBtn: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[5]/div/div[1]/div[1]/div[2]/div[3]")
    lateinit var closeBtn: WebElement

    @FindBy(xpath = "//*[@id=\"cke_16\"]")
    lateinit var boldText: WebElement

  @FindBy(xpath = "//*[@id=\"cke_17\"]")
    lateinit var italicText: WebElement

  @FindBy(xpath = "//*[@id=\"cke_18\"]")
    lateinit var underlineText: WebElement

  @FindBy(xpath = "/html/body/div[10]/div/div/div[2]/button[2]")
    lateinit var dontSave: WebElement
}
