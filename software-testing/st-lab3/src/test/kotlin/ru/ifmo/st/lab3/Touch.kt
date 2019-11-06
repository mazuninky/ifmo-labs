package ru.ifmo.st.lab3

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.LongPressOptions
import io.appium.java_client.touch.TapOptions
import io.appium.java_client.touch.offset.ElementOption
import java.time.Duration

fun AndroidTouchAction.tap(elm: MobileElement) {
    tap(TapOptions.tapOptions()
            .withElement(ElementOption.element(elm))
    ).perform()
}

inline fun AndroidTouchAction.tap(body: () -> MobileElement) {
    tap(body())
}

fun AndroidTouchAction.longTouch(elm: MobileElement, timeIsSeconds: Long) {
    longPress(LongPressOptions.longPressOptions()
            .withDuration(Duration.ofSeconds(timeIsSeconds))
            .withElement(ElementOption.element(elm))
    ).perform()
}

inline fun AndroidTouchAction.longTouch(timeIsSeconds: Long = 1, body: () -> MobileElement) {
    longTouch(body(), timeIsSeconds)
}
