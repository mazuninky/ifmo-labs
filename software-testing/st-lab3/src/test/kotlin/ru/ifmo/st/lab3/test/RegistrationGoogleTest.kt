package ru.ifmo.st.lab3.test

import io.appium.java_client.MobileBy
import io.appium.java_client.touch.TapOptions
import io.appium.java_client.touch.offset.ElementOption
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import ru.ifmo.st.lab3.*
import javax.swing.text.Element

class RegistrationGoogleTest : BaseTest() {
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
//            val btn = driver.xpath { "//*[@text='" + "Create account" + "']" }
//            val btn = driver.find { id("ow248") }
            val btn = driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[5]" }


            wait.until(ExpectedConditions.visibilityOf(btn))
            tap {
                btn
            }


//                touchAction.tap(TapOptions.tapOptions().withPosition(ElementOption.point(45,1650)))
        }


        "Выбираем самостоятельную регистрацию" {
            tap {
                //                    driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[4]/android.view.View/android.view.MenuItem[1]" }
                driver.xpath { "//*[@text='" + "For myself" + "']" }
            }
        }

        "Вводим имя и фамилию" {
            //                val fields = driver.find { id("view_container") }.findAll { className("android.view.View") }

            driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View" }
                    .sendKeys("Keklol")

            driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View" }
                    .sendKeys("dassadasdada")

            tap {
                driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[4]/android.widget.Button" }
            }
        }

        "Вводим данные" {
            tap {
                driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.widget.Spinner" }
            }
            tap {
                driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]" }
            }
            driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View[2]/android.view.View"))
                    .sendKeys("14")

            driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View[1]/android.view.View[3]/android.view.View[2]/android.view.View"))
                    .sendKeys("1998")

            tap {
                driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.Spinner" }
            }

            tap {
                driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]" }
            }
        }

        "Переходим дальше" {
            tap {
                driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[4]/android.widget.Button" }
            }
        }

        "Выбираем email" {
            tap {
                driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View/android.widget.RadioButton[2]" }
            }
        }

        "Подтверждаем выбор email" {
            tap {
                driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[4]/android.widget.Button" }
            }
        }

        "Вводим пароль" {
            val password = (1..12).map { "12345asdbvadsda1233_@".random() }.joinToString("")
            driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View/android.view.View[2]/android.view.View" }
                    .sendKeys(password)
            driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.view.View" }
                    .sendKeys(password)
            driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.view.View" }
                    .sendKeys("\n")
        }

//        "Создаём пароль" {
//            tap {
//                driver.find {id("createpasswordNext")}
////                driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[4]/android.widget.Button" }
//            }
//        }

        "Пропускаем" {
            val skipBTn = driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[5]/android.widget.Button"))
            driver.wait(skipBTn)
            touchAction
                    .press(ElementOption.point(0, 700))
                    .moveTo(ElementOption.point(0, 400))
                    .release()
                    .perform()

            touchAction
                    .press(ElementOption.point(0, 700))
                    .moveTo(ElementOption.point(0, 400))
                    .release()
                    .perform()

//            touchAction
//                    .press(ElementOption.point(0, 250))
//                    .moveTo(ElementOption.point(0, 100))
//                    .release()
//                    .perform()
            tap {
                skipBTn
            }
        }

        "Завершаем" {
            val nextBtn = driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[4]/android.widget.Button" }
//            driver.wait(nextBtn)
            tap {
                nextBtn
            }
//            driver.waitGone(nextBtn)
        }

        "Соглашаемся" {
            val agreeBtn = driver.xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.widget.Button[2]" }
//            driver.wait(agreeBtn)
            touchAction
                    .press(ElementOption.point(0, 600))
                    .moveTo(ElementOption.point(0, 400))
                    .release()
                    .perform()

            touchAction
                    .press(ElementOption.point(0, 600))
                    .moveTo(ElementOption.point(0, 400))
                    .release()
                    .perform()

            touchAction
                    .press(ElementOption.point(0, 600))
                    .moveTo(ElementOption.point(0, 400))
                    .release()
                    .perform()

            tap {
                agreeBtn
            }
        }

    }
}
