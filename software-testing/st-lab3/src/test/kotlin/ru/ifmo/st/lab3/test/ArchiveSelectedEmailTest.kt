package ru.ifmo.st.lab3.test

import io.appium.java_client.MobileBy
import io.appium.java_client.touch.offset.ElementOption
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import ru.ifmo.st.lab3.*

class ArchiveSelectedEmailTest : BaseTest() {
    init {
        "Выделяет письмо"  {
            driver.login()
            tap {
                    driver.xpath { "(//android.view.View[@content-desc=\"Select this conversation\"])[1]" }
            }
            tap {
                driver.find {id("archive")}
            }
        }
    }
}

// unread
