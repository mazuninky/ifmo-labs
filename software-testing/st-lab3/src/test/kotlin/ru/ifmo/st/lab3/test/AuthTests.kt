package ru.ifmo.st.lab3.test

import io.appium.java_client.MobileBy
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.core.env.Environment
import ru.ifmo.st.lab3.*

class AuthTests : BaseTest() {
    init {
        "Авторизация" - {
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

            "Ждём пока закончиться загрузка" {
                val wait = WebDriverWait(driver, 1000)
                val progressBar = driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ProgressBar" }
                wait.until(ExpectedConditions.invisibilityOf(progressBar))
            }

            "Вводим логин" {
                val login = "searchnrun@gmail.com"
                val loginFiled = driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View"))

                loginFiled.sendKeys(login)



                tap {
                    driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[4]/android.widget.Button"))
                }
            }

            "Вводим пароль" {
                val password = System.getenv("PASSWORD")
                driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View"))
                        .sendKeys(password)

                tap {
                    driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[4]/android.widget.Button"))
                }
            }

            "Подтверждаем" {
                val agree =                     driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[4]/android.widget.Button" }
                driver.wait(agree)
                tap {
                    agree
                }
            }
        }
    }
}
