package ru.ifmo.st.lab1.dsl

interface DslLifecycleListener {
    fun onTestingStart()
    fun onTestingFinish()

    fun onTestSuiteStart(name: String)
    fun onTestSuiteFinish()

    fun onTestCaseStart(description: String)
    fun onTestCaseFinish()

    fun onTestStepStart(stepMessage: String, description: String)
    fun onTestStepFinish(status: StepStatus)

    fun onException(e: Exception)
}

sealed class StepStatus

object Ok : StepStatus()

data class Fail(val error: AssertionError) : StepStatus()
