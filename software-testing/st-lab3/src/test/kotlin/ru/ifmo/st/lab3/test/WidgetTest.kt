package ru.ifmo.st.lab3.test

import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.LongPressOptions
import io.appium.java_client.touch.offset.ElementOption
import io.kotlintest.Spec
import io.kotlintest.specs.FreeSpec
import ru.ifmo.st.lab3.*
import java.time.Duration
import java.util.concurrent.TimeUnit

class WidgetTest : FreeSpec() {
    private lateinit var driver: AndroidDriver<MobileElement>
    private lateinit var touchAction: AndroidTouchAction

    override fun beforeSpec(spec: Spec) {
        driver = constructAndroidDriver(LAUNCHER_PACKAGE, LAUNCHER_ACTIVITY)
        touchAction = AndroidTouchAction(driver)
    }

    init {
        "Добавление виджета" - {

            "Вызов контекстного меню" {
                touchAction.longTouch { driver.find { id("launcher") } }
            }

            "Переход в меню виджетов" {
                touchAction.tap {
                    driver.findAll { id("bubble_text") }.filter { it.text == "Widgets" }.first()
                }
            }

            "Выбор виджета" {
                var gmailCell: MobileElement? = null
                var gmailWidget: MobileElement? = null
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)
                while (gmailCell == null) {
                    touchAction
                            .press(ElementOption.point(0, 250))
                            .moveTo(ElementOption.point(0, 100))
                            .release()
                            .perform()
                    try {
                        gmailCell = driver.findAll { id("widgets_cell_list_container") }
                                .first {
                                    try {
                                        val text = it.find { id("section") }.text
                                        println(text)
                                        text == "Gmail"
                                    } catch (e: Exception) {
                                        false
                                    }
                                }

                        try {
                            gmailWidget = gmailCell.findAll { className(WidgetCell) }
                                    .first {
                                        it.find { id("widget_name") }.text == "Gmail"
                                    }
                        } catch (e: Exception) {
                            gmailCell = null
                        }
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }

                requireNotNull(gmailWidget)


                touchAction.longPress(LongPressOptions.longPressOptions()
                        .withDuration(Duration.ofSeconds(5))
                        .withElement(ElementOption.element(gmailWidget))
                ).release()
                        .tap(ElementOption.point(100, 350))
                        .perform()

                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
                if (driver.findElementsByAndroidUIAutomator("new UiSelector().textContains(\"" + "Choose account" + "\")").isNotEmpty()) {
                    touchAction.tap {
                        driver.find { id("list") }.findAll { className(LinearLayout) }.first()
                    }
                }
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)


                touchAction.tap {
                    //                    driver.waitGone(driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout" })

                    driver.find { id("list") }.findAll { className(LinearLayout) }.first()
                }
            }
        }
    }
}
