package ru.ifmo.st.lab3.test

import io.appium.java_client.MobileBy
import io.appium.java_client.touch.TapOptions
import io.appium.java_client.touch.offset.ElementOption
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import ru.ifmo.st.lab3.*
import javax.swing.text.Element

class RegistrationEmptyNameTest : BaseTest() {
    init {

        "Переходим на экран с акканутами" {
            tap {
                driver.find { id("welcome_tour_got_it") }
            }
        }

        "Переходим к экрану добавления аккаунта" {
            tap {
                driver.find { id("setup_addresses_add_another") }
            }
        }

        "Выбираем Google аккаунт" {
            tap {
                driver.find { id("providers_list") }.findAll { className(LinearLayout) }.first()
            }
        }

        "Ждём окончания загрузки" {
            val wait = WebDriverWait(driver, 1000)
            val progressBar = driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ProgressBar" }
            wait.until(ExpectedConditions.invisibilityOf(progressBar))
        }

        "Выбираем создать аккаунт" {
            val wait = WebDriverWait(driver, 1000)
            val btn = driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[5]" }

            wait.until(ExpectedConditions.visibilityOf(btn))
            tap {
                btn
            }
        }


        "Выбираем самостоятельную регистрацию" {
            tap {
                //                    driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[4]/android.view.View/android.view.MenuItem[1]" }
                driver.xpath { "//*[@text='" + "For myself" + "']" }
            }
        }

        "Вводим имя и фамилию" {

            driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View" }
                    .sendKeys("Kodsadsa")

            driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View" }
                    .sendKeys("")


            tap {
                driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[4]/android.widget.Button" }
            }

            //Find error
            driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View[2]" }
        }


    }
}
