package com.example.wwwlapshopin.androidarchitecturepoc

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val validationsLiveData: MutableLiveData<List<Validation>> by lazy {
        MutableLiveData<List<Validation>>()
    }

    val emailValidationLiveData: LiveData<Resource<Any>> by lazy {
        transformValidationLiveData(Validation.Field.EMAIL)
    }

    val passwordValidationLiveData: LiveData<Resource<Any>> by lazy {
        transformValidationLiveData(Validation.Field.PASSWORD)
    }

    val navigationLiveData: LiveData<Boolean> by lazy {
        Transformations.map(validationsLiveData) { validations ->
            validations.isNotEmpty()
                    && validations.filter { it.resource.status == Status.SUCCESS }.size == validations.size
        }
    }

    private fun transformValidationLiveData(field: Validation.Field) =
            Transformations.map(validationsLiveData) {
                it.find { validation -> validation.field == field }
                        ?.run { return@run this.resource }
                        ?: Resource(Status.UNKNOWN)
            }

    fun onLoginClick(email: String, password: String) {
        validationsLiveData.value = Validator.validateLoginFields(email, password)
    }


}