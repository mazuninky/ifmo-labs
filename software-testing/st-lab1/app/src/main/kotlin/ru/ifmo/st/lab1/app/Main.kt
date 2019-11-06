package ru.ifmo.st.lab1.app

import ru.ifmo.st.lab1.app.test.*
import ru.ifmo.st.lab1.dsl.TestFramework

fun main() = TestFramework(closeAtTheEnd = true)
        .add<WelcomePageTest>()
        .add<RegistrationPageTest>()
        .add<AuthPageTest>()
        .add<MailBoxPageTest>()
        .add<CreateMailPageTest>()
        .add<SettingsPageTest>()
        .run()
