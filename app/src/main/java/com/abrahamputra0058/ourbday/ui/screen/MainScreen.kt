package com.abrahamputra0058.ourbday.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abrahamputra0058.ourbday.R
import com.abrahamputra0058.ourbday.ui.theme.OurBDayTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
Scaffold(
    topBar ={
        TopAppBar(
            title = {
                Text( text = stringResource(id = R.string.app_name))
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            actions = {
//                Profil Login
                IconButton(
                    onClick = {}
                ) {
                }
            }
        )
    }
) { innerPadding ->
    ScreenContent(Modifier.padding(innerPadding))
}
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {

    val viewModel: MainViewModel = viewModel()

}

@Composable
fun BirthdayList() {

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    OurBDayTheme {
        MainScreen()
    }
}