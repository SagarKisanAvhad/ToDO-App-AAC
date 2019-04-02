package com.example.wwwlapshopin.androidarchitecturepoc

import android.util.Patterns

fun String.isEmail() = !isBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
