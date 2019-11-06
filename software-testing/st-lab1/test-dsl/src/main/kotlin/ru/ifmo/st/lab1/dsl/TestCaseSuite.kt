package ru.ifmo.st.lab1.dsl

import org.openqa.selenium.WebDriver
import kotlin.contracts.ExperimentalContracts
import kotlin.reflect.jvm.jvmName

abstract class TestCaseSuite(private val body: TestCaseSuiteBody.() -> Unit) {
    fun run(driver: WebDriver, lifecycleListener: DslLifecycleListener) {
        lifecycleListener.onTestSuiteStart(this::class.jvmName)
        try {
            createSuite(body) { testDescriptor ->
                lifecycleListener.onTestCaseStart(testDescriptor.description)
                testDescriptor.createTest(driver) { stepDescriptor ->
                    lifecycleListener.onTestStepStart(stepDescriptor.message, stepDescriptor.description)
                    try {
                        stepDescriptor.execute(driver)
                        lifecycleListener.onTestStepFinish(Ok)
                    } catch (e: AssertionError) {
                        lifecycleListener.onTestStepFinish(Fail(e))
                        return@createSuite
                    }
                }
                lifecycleListener.onTestCaseFinish()
            }
        } catch (e: Exception) {
            lifecycleListener.onException(e)
        }
        lifecycleListener.onTestSuiteFinish()
    }
}

inline fun createSuite(body: TestCaseSuiteBody.() -> Unit,
                       forEachBody: (TestBodyDescriptor) -> Unit): TestCaseSuiteBody {
    return TestCaseSuiteBody()
            .apply(body)
            .apply { testList.forEach(forEachBody) }
}

inline fun TestBodyDescriptor.createTest(driver: WebDriver,
                                         forEachBody: (TestStepBodyDescriptor) -> Unit): TestCaseBody {
    return TestCaseBody(driver)
            .apply(body)
            .apply { stepsList.forEach(forEachBody) }

}

fun TestStepBodyDescriptor.execute(driver: WebDriver): TestStepBody = TestStepBody(driver).apply(body)

val TestStepBodyDescriptor.message: String
    get() = when (this) {
        is WhenStepDescriptor -> "When"
        is ThenStepDescriptor -> "Then"
        is StepDescriptor -> "Step"
    }

@DslTestMarker
class TestCaseSuiteBody {
    private val tests = mutableListOf<TestBodyDescriptor>()
    val testList: List<TestBodyDescriptor> = tests

    fun Test(description: String, body: TestCaseBody.() -> Unit) {
        tests.add(TestBodyDescriptor(description, body))
    }
}
