package ru.ifmo.st.lab3.test

import io.appium.java_client.MobileBy
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import ru.ifmo.st.lab3.*
import kotlin.test.assertTrue

class CreateEmailTest : BaseTest() {
    init {
        "Создание письма" {
            driver.login()
            tap {
                driver.find {id("compose_button")}
            }
        }
    }
}
