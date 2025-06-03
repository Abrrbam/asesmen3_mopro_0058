package com.abrahamputra0058.ourbday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.abrahamputra0058.ourbday.ui.screen.MainScreen
import com.abrahamputra0058.ourbday.ui.theme.OurBDayTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OurBDayTheme {
                MainScreen()
            }
        }
    }
}