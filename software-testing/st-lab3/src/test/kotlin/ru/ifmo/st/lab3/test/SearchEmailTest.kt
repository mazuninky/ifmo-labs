package ru.ifmo.st.lab3.test

import io.appium.java_client.MobileBy
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import ru.ifmo.st.lab3.*

class SearchEmailTest : BaseTest() {
    init {
        "Поиск письма"  {
            driver.login()
            driver.find { id("open_search_bar_text_view") }
                    .sendKeys("test\n")
        }
    }
}
