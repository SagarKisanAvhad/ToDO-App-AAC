package com.example.wwwlapshopin.androidarchitecturepoc

object Validator {

    private const val MIN_PASSWORD_LENGTH = 6

    fun validateLoginFields(email: String, password: String): List<Validation> =
            listOf(
                    email.isEmail().let {
                        return@let if (it) Validation(Validation.Field.EMAIL, Resource(Status.SUCCESS))
                        else Validation(Validation.Field.EMAIL, Resource(Status.ERROR))
                    },
                    (!password.isBlank() && password.length >= MIN_PASSWORD_LENGTH).let {
                        return@let if (it) Validation(Validation.Field.PASSWORD, Resource(Status.SUCCESS))
                        else Validation(Validation.Field.PASSWORD, Resource(Status.ERROR))
                    }
            )
}

data class Validation(val field: Field, val resource: Resource<Any>) {

    enum class Field {
        EMAIL,
        PASSWORD
    }
}