package com.example.nav3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

import com.example.nav3.ui.FirstScreen
import com.example.nav3.ui.Routes
import com.example.nav3.ui.SecondScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    // new feature in Nav3 to handle backStack
    val backStack: SnapshotStateList<Routes> = remember { mutableListOf(Routes.FirstScreen).toMutableStateList() }
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.FirstScreen
    ){
        composable<Routes.FirstScreen> {
            FirstScreen {
                // Navigate to SecondScreen

                // param is the data to send to the secondScreen
                val secondScreen = Routes.SecondScreen(param = "Android")

                // adding secondScreen to backStack
                backStack.add(secondScreen)

                // navigating to secondScreen
                navController.navigate(secondScreen)
            }
        }

        composable<Routes.SecondScreen> { backStackEntry ->

            //param is the data sent from firstScreen
            val secondScreen = backStackEntry.toRoute<Routes.SecondScreen>()

            SecondScreen(
                name = secondScreen.param
            ) {
                // Navigate back to FirstScreen

                // remove the secondScreen from backStack
                backStack.removeLast()

                // navigating to firstScreen
                navController.popBackStack()
            }
        }
    }
}