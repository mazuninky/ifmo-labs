package ru.ifmo.st.lab3

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.TapOptions
import io.appium.java_client.touch.offset.ElementOption
import io.appium.java_client.touch.offset.PointOption
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual

fun AndroidDriver<MobileElement>.login() {
    val touch = AndroidTouchAction(this)

    touch.tap { find { id("welcome_tour_got_it") } }

//    val accountsListView = find { id("setup_addresses_list") }
//    val accountList = accountsListView.findAll { className(LinearLayout) }
//    accountList.size.shouldBeGreaterThanOrEqual(1)

    touch.tap { xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout" }}

    touch.tap { find { id("action_done") } }
    touch.tap { find { id("gm_dismiss_button") } }
    touch.tap { find { id("gm_dismiss_button") } }
}

fun AndroidDriver<MobileElement>.openMenu() {
    val touch = AndroidTouchAction(this)

    login()
    touch.tap {
        xpath { "//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]" }
    }
}

fun AndroidDriver<MobileElement>.openEmail() {
    val touch = AndroidTouchAction(this)

    login()
    touch.tap {
        xpath { "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.View[1]" }
    }

    touch.tap {
        xpath { "(//android.widget.ImageView[@content-desc=\"More options\"])[3]" }
    }

    touch.tap{
        xpath {  "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView" }
    }

    touch.tap {
        find { id("button2")}
    }

//    touch.tap(TapOptions.tapOptions().withPosition(PointOption.point(100,100)))
}

fun AndroidDriver<MobileElement>.createEmail() {
    val touch = AndroidTouchAction(this)

    login()
    touch.tap {
        find { UISelector.id("compose_button") }
    }
    touch.tap {
        find { UISelector.id("button1") }
    }
}

