package ru.ifmo.st.lab1.dsl

import org.openqa.selenium.WebDriver
import ru.ifmo.st.lab1.selenium.chrome
import ru.ifmo.st.lab1.selenium.maximize
import ru.ifmo.st.lab1.selenium.timeout
import java.util.concurrent.TimeUnit
import kotlin.reflect.full.createInstance

class TestFramework(private val driver: WebDriver = chrome().timeout(30, TimeUnit.SECONDS).maximize(),
                    private val lifecycleListener: DslLifecycleListener = ConsoleDslLifecycleListener(withStackTrace = true),
                    private val closeAtTheEnd: Boolean = true) {
    private val testCaseSuites = mutableListOf<TestCaseSuite>()

    fun add(suite: TestCaseSuite): TestFramework = apply { testCaseSuites.add(suite) }

    inline fun <reified T : TestCaseSuite> add(): TestFramework = apply { add(T::class.createInstance()) }

    fun run() {
        lifecycleListener.onTestingStart()
        testCaseSuites.forEach {
            it.run(driver, lifecycleListener)
        }
        if (closeAtTheEnd)
            driver.close()
        lifecycleListener.onTestingFinish()
    }
}
