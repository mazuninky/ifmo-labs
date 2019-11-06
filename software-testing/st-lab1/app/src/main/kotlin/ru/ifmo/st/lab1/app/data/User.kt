package ru.ifmo.st.lab1.app.data

data class User(val login: String, val password: String)

val defaultUser = User("konstantin@tusion.xyz", System.getenv("EMAIL_PASSWORD"))
