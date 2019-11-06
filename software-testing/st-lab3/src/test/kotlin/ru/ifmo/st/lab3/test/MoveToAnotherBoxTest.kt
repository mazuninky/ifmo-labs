package ru.ifmo.st.lab3.test

import io.appium.java_client.MobileBy
import io.appium.java_client.touch.offset.ElementOption
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import ru.ifmo.st.lab3.*

class MoveToAnotherBoxTest : BaseTest() {
    init {
        "Переносит письмо"  {
            driver.login()
            tap {
                driver.xpath { "(//android.view.View[@content-desc=\"Select this conversation\"])[1]" }
            }
            tap {
                driver.xpath { "//android.widget.ImageView[@content-desc=\"More options\"]" }
            }
            tap {
                driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout" }
            }
            tap {
                driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v7.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]" }
            }
        }
    }
}
