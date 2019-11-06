package ru.ifmo.st.lab3.test

import io.appium.java_client.MobileBy
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import ru.ifmo.st.lab3.*

class DeleteCurrentEmailTest : BaseTest() {
    init {
        "Удаление текущего письма"  {
            driver.openEmail()

            tap { driver.find { id("delete") } }
        }
    }
}
