package com.example.nav3.ui

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object FirstScreen : Routes()

    @Serializable
    data class SecondScreen(val param: String) : Routes()
}