package ru.ifmo.st.lab3.test

import io.appium.java_client.MobileBy
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import ru.ifmo.st.lab3.*

class OpenInboxTests : BaseTest() {
    init {
        "Логин" - {
            "Переходим на экран с акканутами" {
                tap {
                    driver.find { UISelector.id("welcome_tour_got_it") }
                }
            }

            "Проверяем, что списко акканутов не пуст" {
                val accountsListView = driver.find { UISelector.id("setup_addresses_list") }
                val accountList = accountsListView.findAll { UISelector.className(LinearLayout) }
                accountList.size.shouldBeGreaterThanOrEqual(1)
            }

            "Переходим на экран с письмами" {
                tap {
                    driver.find { UISelector.id("action_done") }
                }
            }

            "Закрываем всплывающее окно" {
                tap {
                    driver.find { UISelector.id("gm_dismiss_button") }
                }
            }

            "Проверяем наличие вариантов для выбора" {
                driver.find { UISelector.id("default_button") }
                driver.find { UISelector.id("comfortable_button") }
                driver.find { UISelector.id("compact_button") }
            }

            "Выбираем вид списка с письмами" {
                tap {
                    driver.find { UISelector.id("gm_dismiss_button") }
                }
            }
        }
    }
}
