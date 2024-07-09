package com.example.spendwise

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.presentation.add_edit.add.AddScreen
import com.example.spendwise.presentation.add_edit.edit.EditScreen
import com.example.spendwise.presentation.components.SpendWiseTabRow
import com.example.spendwise.presentation.detail.DetailScreen
import com.example.spendwise.presentation.expenses.ExpensesScreen
import com.example.spendwise.presentation.overview.OverviewScreen
import com.example.spendwise.ui.theme.SpendWiseTheme
import dagger.hilt.android.AndroidEntryPoint

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@AndroidEntryPoint
class SpendWiseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpendWiseApp()
        }
    }
}

@Composable
fun SpendWiseApp() {
    SpendWiseTheme {
        val navController = rememberNavController()
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry?.destination
        val currentScreen = SpendWiseTabRowScreens.find { it.route == currentDestination?.route } ?: Overview
        Scaffold(
            topBar = {
                if (currentDestination?.route == Overview.route ||
                    currentDestination?.route == Expenses.route ||
                    currentDestination?.route == Add.route){
                    SpendWiseTabRow(
                        allScreens = SpendWiseTabRowScreens,
                        onTabSelected = { navController.navigateSingleTopTo(it.route) },
                        currentScreen = currentScreen
                    )
                } else {
                    Row(modifier = Modifier.heightIn(max = 56.dp)) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            SpendWiseNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun SpendWiseNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ) {
        composable(route = Overview.route){

            OverviewScreen(
                onClickSeeAll = {
                    navController.navigateToDetailScreen("All")
                }
            )
        }

        composable(route = Expenses.route){
            ExpensesScreen(
                onClickSeeAll = {
                    navController.navigateToDetailScreen(it)
                },
                onItemEditClicked = {
                    navController.navigateToEditScreen(it)
                }
            )
        }

        composable(
            route = Add.route
        ){
            AddScreen()
        }

        composable(
            route = Edit.routeWithArgs,
            arguments = Edit.arguments
        ){  navBackStackEntry ->

            val navArg = navBackStackEntry.arguments?.getInt(Edit.editArg)
            if (navArg != null){
                EditScreen(
                    argumentData = navArg,
                    navigateUp = {
                        navController.navigateUp()
                    }
                )
            }
        }

        composable(
            route = Detail.routeWithArgs,
            arguments = Detail.arguments
        ){  navBackStackEntry ->

            val navArg = navBackStackEntry.arguments?.getString(Detail.detailArg)
            if (navArg != null){
                DetailScreen(
                    argumentData = navArg,
                    editItemOnClicked = {
                        navController.navigateToEditScreen(it)
                    }
                )
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }


private fun NavHostController.navigateToDetailScreen(detailArg: String) {
    this.navigate("${Detail.route}/$detailArg"){
        launchSingleTop = true
    }
}

private fun NavHostController.navigateToEditScreen(editArg: Int) {
    this.navigate("${Edit.route}/$editArg"){
        launchSingleTop = true
    }
}