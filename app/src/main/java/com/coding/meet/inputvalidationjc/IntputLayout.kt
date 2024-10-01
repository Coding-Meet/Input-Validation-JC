package com.coding.meet.inputvalidationjc

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

/**
 * Created 26-09-2024 at 03:05 pm
 */


@Composable
fun OutlineFieldWithState(
    modifier: Modifier = Modifier,
    label: String,
    fieldInput: FieldInput,
    errorStatus: ErrorStatus,
    keyboardOptions: KeyboardOptions,
    isPasswordField: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIconResource: IconResource? = null,
    onValueChange: (String) -> Unit,
) {

    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier,
        value = fieldInput.value,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
        },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        leadingIcon = leadingIconResource?.let {
            {
                Icon(it.asPainterResource(), contentDescription = null)
            }
        },
        isError = fieldInput.hasInteracted && errorStatus.isError,
        supportingText = {
            if (fieldInput.hasInteracted && errorStatus.isError) {
                errorStatus.errorMsg?.let {
                    Text(
                        text = it.asString(), modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        trailingIcon = {
            if (isPasswordField) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = if (passwordVisible) painterResource(R.drawable.ic_visibility)
                        else painterResource(R.drawable.ic_visibility_off),
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            } else if (fieldInput.hasInteracted && errorStatus.isError) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = null)
            }
        },
        visualTransformation = if (isPasswordField && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None
    )
}


