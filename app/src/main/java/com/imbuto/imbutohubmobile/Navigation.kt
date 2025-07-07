package com.imbuto.imbutohubmobile

sealed class Screen(val route: String) {
    object Choose : Screen("choose")
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Confirmation : Screen("confirmation")
}