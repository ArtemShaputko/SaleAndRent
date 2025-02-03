package com.project.saleandrent.enteractivity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.saleandrent.R
import com.project.saleandrent.ui.theme.SaleAndRentTheme

@Composable
fun First(onLogInPressed: () -> Unit,
          onSignUpPressed: () -> Unit,
          modifier: Modifier = Modifier) {
    Column(modifier = modifier){
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                style = MaterialTheme.typography.displayLarge,
                color = colorResource(id = R.color.main)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = colorResource(id = R.color.second),
                    disabledContainerColor = MaterialTheme.colorScheme.background,
                    disabledContentColor = colorResource(id = R.color.second)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 10.dp),
                onClick = onLogInPressed,
                ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                colors = ButtonColors(
                    containerColor = colorResource(id = R.color.second),
                    contentColor = Color.White,
                    disabledContainerColor = colorResource(id = R.color.second),
                    disabledContentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 10.dp),
                onClick = onSignUpPressed) {
                Text(
                    text = "Sign up",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstPreview() {
    SaleAndRentTheme {
        First({},{})
    }
}