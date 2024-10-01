package com.coding.meet.inputvalidationjc

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * Created 10-09-2024 at 05:22 pm
 */

class InputViewModel : ViewModel() {

    // Name field
    var nameField by mutableStateOf(FieldInput())
    val nameErrorStatus by derivedStateOf {
        validateName(nameField.value)
    }

    // Email field
    var emailField by mutableStateOf(FieldInput())
    val emailErrorStatus by derivedStateOf {
        validateEmail(emailField.value)
    }

    // Password field
    var passwordField by mutableStateOf(FieldInput())
    val passwordErrorStatus by derivedStateOf {
        validatePassword(passwordField.value)
    }

    // Confirm Password field
    var conPasswordField by mutableStateOf(FieldInput())
    val conPasswordErrorStatus by derivedStateOf {
        validateConPassword(passwordField.value, conPasswordField.value)
    }
}