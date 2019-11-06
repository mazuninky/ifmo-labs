package ru.ifmo.st.lab1.dsl

import org.openqa.selenium.WebDriver
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

fun TestCaseBody.When(description: String, body: TestStepBody.() -> Unit) {
    contract {
        callsInPlace(body, InvocationKind.EXACTLY_ONCE)
    }
    addStepDescriptor(WhenStepDescriptor(description, body))
}

fun TestCaseBody.Then(description: String, body: TestStepBody.() -> Unit) {
    contract {
        callsInPlace(body, InvocationKind.EXACTLY_ONCE)
    }
    addStepDescriptor(ThenStepDescriptor(description, body))
}

fun TestCaseBody.Step(description: String, body: TestStepBody.() -> Unit) {
    contract {
        callsInPlace(body, InvocationKind.EXACTLY_ONCE)
    }
    addStepDescriptor(StepDescriptor(description, body))
}

@DslTestMarker
class TestCaseBody(val driver: WebDriver) {
    private val steps = mutableListOf<TestStepBodyDescriptor>()

    fun addStepDescriptor(descriptor: TestStepBodyDescriptor) {
        steps.add(descriptor)
    }

    val stepsList: List<TestStepBodyDescriptor> = steps

//    fun When(description: String, body: TestStepBody.() -> Unit) {
//        addStepDescriptor(ThenStepDescriptor(description, body))
//    }

//    fun Then(description: String, body: TestStepBody.() -> Unit) {
//        steps.add(ThenStepDescriptor(description, body))
//    }

//    fun Step(description: String, body: TestStepBody.() -> Unit) {
//        steps.add(StepDescriptor(description, body))
//    }
}

data class TestBodyDescriptor(val description: String, val body: TestCaseBody.() -> Unit)
