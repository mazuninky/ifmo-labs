package ru.ifmo.st.lab1.selenium

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit

inline fun chrome() = ChromeDriver()

inline fun firefox() = FirefoxDriver()

inline fun WebDriver.timeout(time: Long, unit: TimeUnit): WebDriver = apply {
    manage().timeouts().implicitlyWait(time, unit)
}

inline fun WebDriver.maximize(): WebDriver = apply {
    manage().window().maximize()
}

inline fun WebDriver.get(url: String, body: WebDriver.() -> Unit) {
    get(url)
    apply(body)
}
