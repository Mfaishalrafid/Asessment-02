package org.d3if3073.asesmen3.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3073.asesmen3.ui.screen.AboutScreen
import org.d3if3073.asesmen3.ui.screen.ApiScreen
//import org.d3if3073.asesmen3.ui.screen.ApiScreen
import org.d3if3073.asesmen3.ui.screen.DetailScreen
import org.d3if3073.asesmen3.ui.screen.HausScreen
import org.d3if3073.asesmen3.ui.screen.HomeScreen
import org.d3if3073.asesmen3.ui.screen.MainScreen
import org.d3if3073.asesmen3.ui.screen.MenuScreen

sealed class Screen(val route: String) {
    object Home: Screen("homeScreen")
    object Main: Screen("mainScreen")
    object Data: Screen("mainScreen")
    object Api: Screen("apiScreen")
    object FormBaru: Screen("detailScreen")
    data object About: Screen("aboutScreen")
    data object Haus: Screen("hausScreen")
    data object Menu: Screen("menuScreen")
    object FormUbah : Screen("detailScreen/{id}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Api.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.Haus.route) {
            HausScreen(navController)
        }
        composable(route = Screen.Menu.route) {
            MenuScreen(navController)
        }
        composable(route = Screen.Data.route) {
            MainScreen(navController)
        }
        composable(route = Screen.Api.route) {
            ApiScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val id = arguments.getLong("id")
            DetailScreen(navController, id)
        }
    }
}