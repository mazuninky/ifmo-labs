package ru.ifmo.st.lab3.test

import io.appium.java_client.MobileBy
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import ru.ifmo.st.lab3.*

class SocialBoxTest : BaseTest() {
    init {
        "Переход в ящик с социальными сообщениями"  {
            driver.openMenu()
            tap {
                driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout[2]/android.widget.ListView/android.widget.LinearLayout[3]" }
            }
        }
    }
}
