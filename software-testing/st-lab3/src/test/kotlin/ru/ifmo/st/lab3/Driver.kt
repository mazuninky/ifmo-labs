package ru.ifmo.st.lab3

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL
import java.util.concurrent.TimeUnit

fun constructAndroidDriver(appPackage: String, appActivity: String, waitInSeconds: Long = 30): AndroidDriver<MobileElement> {
    val capabilities = DesiredCapabilities()
    capabilities.setCapability("device", "Android")
    capabilities.setCapability("deviceName", "dev")

    capabilities.setCapability("appPackage", appPackage)
    capabilities.setCapability("appActivity", appActivity);

    val driver = AndroidDriver<MobileElement>(URL("http://0.0.0.0:4723/wd/hub"), capabilities)
    driver.manage().timeouts().implicitlyWait(waitInSeconds, TimeUnit.SECONDS)

    return driver
}

const val GM_PACKAGE = "com.google.android.gm"
const val GM_ACTIVITY = "com.google.android.gm.ui.MailActivityGmail"

const val LAUNCHER_PACKAGE = "com.google.android.apps.nexuslauncher"
const val LAUNCHER_ACTIVITY = "com.google.android.apps.nexuslauncher.NexusLauncherActivity"
