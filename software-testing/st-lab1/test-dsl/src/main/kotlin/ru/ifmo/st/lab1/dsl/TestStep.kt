package ru.ifmo.st.lab1.dsl

import org.openqa.selenium.WebDriver

@DslTestMarker
class TestStepBody(val driver: WebDriver)

sealed class TestStepBodyDescriptor {
    abstract val description: String
    abstract val body: TestStepBody.() -> Unit
}

data class WhenStepDescriptor(override val description: String,
                              override val body: TestStepBody.() -> Unit) : TestStepBodyDescriptor()

data class ThenStepDescriptor(override val description: String,
                              override val body: TestStepBody.() -> Unit) : TestStepBodyDescriptor()

data class StepDescriptor(override val description: String,
                          override val body: TestStepBody.() -> Unit) : TestStepBodyDescriptor()
