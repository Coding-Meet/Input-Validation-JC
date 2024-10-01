package com.coding.meet.inputvalidationjc

import android.content.Context
import android.widget.Toast


/**
 * Created 02-04-2024 at 04:15 pm
 */

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}


fun validateName(name: String): ErrorStatus {
    return when {
        name.trim().isEmpty() -> {
            ErrorStatus(true, UiText.StringResource(R.string.required))
        }

        else -> {
            ErrorStatus(false)
        }
    }
}

fun validateEmail(email: String): ErrorStatus {
    val emailPattern = Regex("[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+")
    return when {
        email.trim().isEmpty() -> {
            ErrorStatus(true, UiText.StringResource(R.string.required))
        }

        !email.trim().matches(emailPattern) -> {
            ErrorStatus(true, UiText.StringResource(R.string.valid_e_mail))
        }

        else -> {
            ErrorStatus(false)
        }
    }
}

fun validatePassword(password : String) : ErrorStatus{
    return when {
        password.trim().isEmpty() -> {
            ErrorStatus(true, UiText.StringResource(R.string.required))
        }
        password.trim().length < 8  || password.trim().length > 10 -> {
            ErrorStatus(true, UiText.StringResource(R.string.password_must_be_8_to_10_character))
        }
        else -> {
            ErrorStatus(false)
        }
    }
}

fun validateConPassword(password : String,conPassword : String) : ErrorStatus{
    return when {
        conPassword.trim().isEmpty() -> {
            ErrorStatus(true, UiText.StringResource(R.string.required))
        }
        conPassword.trim().length < 8  || conPassword.trim().length > 10 -> {
            ErrorStatus(true, UiText.StringResource(R.string.password_must_be_8_to_10_character))
        }
        password.trim() != conPassword.trim() -> {
            ErrorStatus(true, UiText.StringResource(R.string.password_don_t_match))
        }
        else -> {
            ErrorStatus(false)
        }
    }
}

