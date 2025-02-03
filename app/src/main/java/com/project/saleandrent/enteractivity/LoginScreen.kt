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
import androidx.compose.runtime.saveable.rememberSaveable
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
fun LoginScreen(
    context: Context,
    isPasswordValid: (String) -> Boolean,
    modifier: Modifier = Modifier
) {

    val userName = rememberSaveable{
        mutableStateOf("")
    }
    val userPassword = rememberSaveable {
        mutableStateOf("")
    }
    val isValid = rememberSaveable {
        mutableStateOf(true)
    }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding( horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Entrace",
            style = MaterialTheme.typography.displayMedium,
            color = colorResource(id = R.color.main),
            modifier = Modifier
        )

        OutlinedTextField(
            value = userName.value, onValueChange = {
                userName.value = it
            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "person")
            },
            label = {
                Text(text = "username")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
        )

        // Password input field
        OutlinedTextField(
            value = userPassword.value, onValueChange = {
                userPassword.value = it
            },
            leadingIcon = {
                Icon(Icons.Default.Info, contentDescription = "password")
            },
            label = {
                Text(text = "password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        if (!isValid.value) {
            Text(
                text = "Invalid name or password",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(8. dp)
            )
        }

        OutlinedButton(
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = colorResource(id = R.color.second),
                disabledContainerColor = MaterialTheme.colorScheme.background,
                disabledContentColor = colorResource(id = R.color.second)
            ),
            onClick = {
                if(isPasswordValid(userPassword.value) && userName.value.isNotEmpty()) {
                    val intent = Intent(context, MainActivity::class.java).apply {
                        putExtra("Login", true)
                    }
                    context.startActivity(intent)
                    (context as? Activity)?.finish()
                } else {
                    isValid.value = false
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 25.dp, 0.dp, 0.dp)
        ) {
            Text(
                text = "Login",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}
