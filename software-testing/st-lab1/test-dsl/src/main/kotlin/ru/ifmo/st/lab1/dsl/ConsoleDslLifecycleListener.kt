package ru.ifmo.st.lab1.dsl

const val ANSI_RESET = "\u001B[0m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"

class ConsoleDslLifecycleListener(val withStackTrace: Boolean = false) : DslLifecycleListener {
    override fun onTestingStart() {
        println("Run tests")
    }

    override fun onTestingFinish() {
        println("Testing finished")
    }

    override fun onTestSuiteStart(name: String) {
        println("\nRun TestSuite: $name")
    }

    override fun onTestSuiteFinish() {

    }

    override fun onException(e: Exception) {
        println("\t${ANSI_RED}Got exception: $e${ANSI_RESET}")
        if (withStackTrace) {
            e.printStackTrace()
        }
    }

    override fun onTestCaseStart(description: String) {
        println("\tTest case: $description")
    }

    override fun onTestCaseFinish() {
        println("\t${ANSI_GREEN}Success${ANSI_RESET}")
        println()
    }

    override fun onTestStepStart(stepMessage: String, description: String) {
        println("\t\t$stepMessage: $description")
    }

    override fun onTestStepFinish(status: StepStatus) {
        if (status is Fail) {
            println("\t${ANSI_RED}Test failed with: ${status.error.message}${ANSI_RESET}")
            println()
        }
    }
}
