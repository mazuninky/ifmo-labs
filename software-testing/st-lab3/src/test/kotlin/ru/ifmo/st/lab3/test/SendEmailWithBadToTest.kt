package ru.ifmo.st.lab3.test

import io.appium.java_client.MobileBy
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import ru.ifmo.st.lab3.*

class SendEmailWithBadToTest : BaseTest() {
    init {
        "Отправка письма"  {
            driver.createEmail()

            driver.find { id("to") }.sendKeys("'s me - Mario!")
            tap { driver.find { id("add_cc_bcc") } }
            driver.find { id("subject") }.sendKeys("test")
            driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.widget.EditText" }
                    .sendKeys("It's me - Mario!")

            tap { driver.find { id("send") } }


            tap {driver.find{id("button1")}}
        }
    }
}
