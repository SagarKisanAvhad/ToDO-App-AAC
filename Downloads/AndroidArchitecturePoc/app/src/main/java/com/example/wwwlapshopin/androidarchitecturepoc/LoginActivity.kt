package com.example.wwwlapshopin.androidarchitecturepoc

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.wwwlapshopin.androidarchitecturepoc.R.id.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var email: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupView()

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        loginViewModel.emailValidationLiveData.observe(this, Observer {
            if (it.status == Status.ERROR) email.setTextColor(Color.RED)
            else email.setTextColor(Color.BLACK)
        })

        loginViewModel.passwordValidationLiveData.observe(this, Observer {
            if (it.status == Status.ERROR) password.setTextColor(Color.RED)
            else password.setTextColor(Color.BLACK)
        })

        loginViewModel.navigationLiveData.observe(this, Observer {
            if (it) Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
        })


    }


    private fun setupView() {
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)

        val button = findViewById<Button>(R.id.btLogin)

        button.setOnClickListener {
            loginViewModel.onLoginClick(email.text.toString(), password.text.toString())
        }

        email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                email.setTextColor(Color.BLACK)
            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                password.setTextColor(Color.BLACK)
            }
        })
    }
}