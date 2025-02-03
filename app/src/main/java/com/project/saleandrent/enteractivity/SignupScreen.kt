package com.project.saleandrent.enteractivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.saleandrent.MainActivity
import com.project.saleandrent.R

@Composable
fun SignupScreen(context: Context,
                 isPasswordValid: (String) -> Boolean,
                 isEmailValid: (String) -> Boolean,
                 modifier: Modifier = Modifier) {

    val userName = remember {
        mutableStateOf("")
    }
    val userPassword = remember {
        mutableStateOf("")
    }
    val userRepeatedPassword = remember {
        mutableStateOf("")
    }
    val userEmail = remember {
        mutableStateOf("")
    }
    val nameValid = remember {
        mutableStateOf(true)
    }
    val passwordValid = remember {
        mutableStateOf(true)
    }
    val emailValid = remember {
        mutableStateOf(true)
    }
    val errorNameText = remember {
        mutableStateOf("")
    }
    val errorEmailText = remember {
        mutableStateOf("")
    }
    val errorPasswordText = remember {
        mutableStateOf("")
    }

    Column(modifier = modifier
        .fillMaxHeight()
        .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(text = "Registration",
            style = MaterialTheme.typography.displayMedium,
            color = colorResource(id = R.color.main),
            modifier = Modifier
        )

        OutlinedTextField(value = userName.value, onValueChange = {
            userName.value = it
        },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "person")
            },
            label = {
                Text(text = if(nameValid.value) "username" else errorNameText.value)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
            isError = !nameValid.value
        )

        OutlinedTextField(value = userEmail.value, onValueChange = {
            userEmail.value = it
        },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "email")
            },
            label = {
                Text(text = if(emailValid.value) "email" else errorEmailText.value)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
            isError = !emailValid.value
        )

        OutlinedTextField(value = userPassword.value, onValueChange = {
            userPassword.value = it
        },
            leadingIcon = {
                Icon(Icons.Default.Info, contentDescription = "password")
            },
            label = {
                Text(text = if(passwordValid.value) "password" else errorPasswordText.value)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
            visualTransformation = PasswordVisualTransformation(),
            isError = !passwordValid.value
        )

        OutlinedTextField(value = userRepeatedPassword.value, onValueChange = {
            userRepeatedPassword.value = it
        },
            leadingIcon = {
                Icon(Icons.Default.Info, contentDescription = "password")
            },
            label = {
                Text(text = "Repeate password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
            visualTransformation = PasswordVisualTransformation(),
            isError = !passwordValid.value
        )


        OutlinedButton(onClick = {
            if(userName.value.isEmpty()) {
                nameValid.value = false
                errorNameText.value = "Name shouldn`t be empty"
            } else {
                nameValid.value = true
            }
            if(!isEmailValid(userEmail.value)) {
                emailValid.value = false
                errorEmailText.value = "Wrong email"
            } else {
                emailValid.value = true
            }
            if(!isPasswordValid(userPassword.value)) {
                passwordValid.value = false
                errorPasswordText.value = "Password: at least 8 symbols, 1 uppercase and 1 number"
            } else {
                if(userPassword.value == userRepeatedPassword.value) {
                    passwordValid.value = true
                } else {
                    passwordValid.value = false
                    errorPasswordText.value = "Passwords don`t match"
                }
            }
            if(emailValid.value && passwordValid.value && nameValid.value) {
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra("Login", true)
                }
                context.startActivity(intent)
                (context as? Activity)?.finish()
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 25.dp, 0.dp, 0.dp),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = colorResource(id = R.color.second),
                disabledContainerColor = MaterialTheme.colorScheme.background,
                disabledContentColor = colorResource(id = R.color.second)
            )
        ) {
            Text(text = "Sign up",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}
