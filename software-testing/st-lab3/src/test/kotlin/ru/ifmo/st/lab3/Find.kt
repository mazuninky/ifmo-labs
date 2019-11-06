package ru.ifmo.st.lab3

import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.MobileSelector
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.WebElement

object UISelector {
    private const val selectorBegin = "new UiSelector()"
    fun id(id: String) = "$selectorBegin.resourceIdMatches(\".*:id/$id\")"
    fun resourceId(id: String) = "$selectorBegin.resourceId(\"$id\")"
    fun className(name: String) = "$selectorBegin.className(\"$name\")"
    fun text(text: String) = "$selectorBegin.text(\"$text\")"
}

object UiScrollable {
    private const val selectorBegin = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("
    private const val selectorEnd = ".instance(0))"


    fun toSelector(selector: String) = "${selectorBegin}${selector}${selectorEnd}"
    fun toId(id: String) = "${selectorBegin}${UISelector.id(id)}${selectorEnd}"
    fun toText(text: String) = "${selectorBegin}${UISelector.text(text)}${selectorEnd}"
}

const val LinearLayout = "android.widget.LinearLayout"
const val WidgetCell = "com.android.launcher3.widget.WidgetCell"

inline fun <T : MobileElement> AndroidDriver<T>.find(body: UISelector.() -> String): T {
    return findElementByAndroidUIAutomator(UISelector.body())
}

inline fun <T : MobileElement> AndroidDriver<T>.xpath(body: () -> String): T {
    return findElement(MobileBy.xpath(body()))
}

inline fun <T : MobileElement> AndroidDriver<T>.findAll(body: UISelector.() -> String): List<T> {
    return findElementsByAndroidUIAutomator(UISelector.body())
}

inline fun MobileElement.find(body: UISelector.() -> String): MobileElement {
    return findElement(MobileSelector.ANDROID_UI_AUTOMATOR.toString(), UISelector.body())
}

inline fun MobileElement.findAll(body: UISelector.() -> String): List<MobileElement> {
    return findElements(MobileSelector.ANDROID_UI_AUTOMATOR.toString(), UISelector.body())
}
