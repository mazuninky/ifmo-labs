package ru.ifmo.st.lab1.app.page

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import ru.ifmo.st.lab1.selenium.bySelector
import ru.ifmo.st.lab1.selenium.elementExists

class Email(private val element: WebElement, private val checkBox: WebElement) {
    fun click() {
        element.click()
    }

    fun toggle() {
        checkBox.click()
    }

    val message: String = element.text
}

class MailBoxPage : WebPage() {
    override val url: String = mailBoxUrl

    @FindBy(css = "a[href$='#inbox']")
    lateinit var inboxBtn: WebElement

    @FindBy(css = "a[href$='#sent']")
    lateinit var sendBox: WebElement

    @FindBy(css = "a[href$='#spam']")
    lateinit var spamBox: WebElement

    @FindBy(css = "a[href$='#trash']")
    lateinit var trashBox: WebElement

    @FindBy(css = "a[href$='#draft']")
    lateinit var draftBox: WebElement

    @FindBy(className = "mail-NestedList-Item_current")
    lateinit var selectedItem: WebElement

    @FindBy(css = "div.mail-MessagesList")
    lateinit var messageList: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[5]")
    lateinit var toolbarDelete: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[4]")
    lateinit var toolbarResend: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[6]")
    lateinit var toolbarSpam: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[8]")
    lateinit var toolbarRead: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[11]")
    lateinit var toolbarTag: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[12]")
    lateinit var toolbarFolder: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[3]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[13]")
    lateinit var toolbarPin: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[2]/div[2]/div/div/a")
    lateinit var createEmailBtn: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[2]/div[2]/div/div/span")
    lateinit var refreshEmailBtn: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[2]/div[3]/div/div[1]/div[2]/span")
    lateinit var createFolderBtn: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[3]/div[2]/div[3]/div/div[4]/span")
    lateinit var createTagBtn: WebElement

    @FindBy(xpath = "//*[@id=\"nb-3\"]")
    lateinit var settingsBtn: WebElement

    @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[2]/div[2]/a[1]")
    lateinit var contactsBtn: WebElement

    val messages: List<Email> by lazy {
        messageList.findElements(By.cssSelector("div.mail-MessageSnippet-Wrap")).map {
            Email(it.bySelector(".mail-MessageSnippet-Content .mail-MessageSnippet-Item_subject span:last-child") {},
                    it.findElement(By.cssSelector(".mail-MessageSnippet-CheckboxNb-Container"))
            )
        }
    }

    val isNotEmpty by lazy { messages.isNotEmpty() }

    fun open(number: Int) {
        messages[number].click()
    }

    fun select(number: Int) {
        messages[number].toggle()
    }
}
