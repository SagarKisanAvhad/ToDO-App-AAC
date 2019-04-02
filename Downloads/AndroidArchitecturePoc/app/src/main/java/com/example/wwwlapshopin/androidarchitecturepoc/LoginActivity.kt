package com.example.wwwlapshopin.androidarchitecturepoc

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.wwwlapshopin.androidarchitecturepoc.R.id.*

class LoginActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        loginViewModel.emailValidationLiveData.observe(this, Observer {
            if (it.status == Status.ERROR) etEmail.setTextColor(Color.RED)
            else etEmail.setTextColor(Color.BLACK)
        })

        loginViewModel.passwordValidationLiveData.observe(this, Observer {
            if (it.status == Status.ERROR) etPassword.setTextColor(Color.RED)
            else etPassword.setTextColor(Color.BLACK)
        })

        loginViewModel.navigationLiveData.observe(this, Observer {
            if (it) Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
        })

        setupView()
    }

    private fun setupView() {
        btLogin.setOnClickListener {
            loginViewModel.onLoginClick(etEmail.text.toString(), etPassword.text.toString())
        }

        etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                etEmail.setTextColor(Color.BLACK)
            }
        })

        etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                etPassword.setTextColor(Color.BLACK)
            }
        })
    }
}