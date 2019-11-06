package ru.ifmo.st.lab3

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import io.kotlintest.Spec
import io.kotlintest.TestCaseConfig
import io.kotlintest.specs.FreeSpec

abstract class BaseTest : FreeSpec() {
    protected lateinit var driver: AndroidDriver<MobileElement>
    protected lateinit var touchAction: AndroidTouchAction

    override val defaultTestCaseConfig = TestCaseConfig(invocations = 1)

    protected fun tap(elm: MobileElement) {
        touchAction.tap(elm)
    }

    protected inline fun tap(body: () -> MobileElement) {
        tap(body())
    }

    override fun beforeSpec(spec: Spec) {
        driver = constructAndroidDriver(GM_PACKAGE, GM_ACTIVITY)
        touchAction = AndroidTouchAction(driver)
    }
}
