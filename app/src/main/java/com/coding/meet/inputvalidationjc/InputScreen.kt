package com.coding.meet.inputvalidationjc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Created 10-09-2024 at 05:20 pm
 */

@Composable
fun InputScreen() {
    val context = LocalContext.current
    val inputViewModel: InputViewModel = viewModel()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            item {
                OutlineFieldWithState(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(R.string.enter_the_name),
                    fieldInput = inputViewModel.nameField,
                    errorStatus = inputViewModel.nameErrorStatus,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                ) {
                    inputViewModel.nameField = inputViewModel.nameField.copy(
                        value = it,
                        hasInteracted = true
                    )
                }
            }
            item {
                OutlineFieldWithState(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(R.string.enter_the_email),
                    fieldInput = inputViewModel.emailField,
                    errorStatus = inputViewModel.emailErrorStatus,
                    leadingIconResource = IconResource.fromImageVector(Icons.Filled.Email),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                ) {
                    inputViewModel.emailField = inputViewModel.emailField.copy(
                        value = it,
                        hasInteracted = true
                    )
                }
            }
            item {
                OutlineFieldWithState(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(R.string.enter_the_password),
                    fieldInput = inputViewModel.passwordField,
                    errorStatus = inputViewModel.passwordErrorStatus,
                    leadingIconResource = IconResource.fromImageVector(Icons.Filled.Lock),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    isPasswordField = true
                ) {
                    inputViewModel.passwordField = inputViewModel.passwordField.copy(
                        value = it,
                        hasInteracted = true
                    )
                }
            }
            item {
                OutlineFieldWithState(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(R.string.enter_the_confirm_password),
                    fieldInput = inputViewModel.conPasswordField,
                    errorStatus = inputViewModel.conPasswordErrorStatus,
                    leadingIconResource = IconResource.fromImageVector(Icons.Filled.Lock),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    isPasswordField = true
                ) {
                    inputViewModel.conPasswordField = inputViewModel.conPasswordField.copy(
                        value = it,
                        hasInteracted = true
                    )
                }
            }
            item {
                Button(
                    onClick = {
                        if (inputViewModel.nameErrorStatus.isError){
                            inputViewModel.nameField = inputViewModel.nameField.copy(
                                hasInteracted = true
                            )
                            inputViewModel.nameErrorStatus.errorMsg?.let {
                                context.showToast(it.asString(context))
                            }
                            return@Button
                        }
                        if (inputViewModel.emailErrorStatus.isError){
                            inputViewModel.emailField = inputViewModel.emailField.copy(
                                hasInteracted = true
                            )
                            inputViewModel.emailErrorStatus.errorMsg?.let {
                                context.showToast(it.asString(context))
                            }
                            return@Button
                        }
                        if (inputViewModel.passwordErrorStatus.isError){
                            inputViewModel.passwordField = inputViewModel.passwordField.copy(
                                hasInteracted = true
                            )
                            inputViewModel.passwordErrorStatus.errorMsg?.let {
                                context.showToast(it.asString(context))
                            }
                            return@Button
                        }
                        if (inputViewModel.conPasswordErrorStatus.isError){
                            inputViewModel.conPasswordField = inputViewModel.conPasswordField.copy(
                                hasInteracted = true
                            )
                            inputViewModel.conPasswordErrorStatus.errorMsg?.let {
                                context.showToast(it.asString(context))
                            }
                            return@Button
                        }

                        context.showToast("Success")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.submit))
                }
            }
        }
    }
}