package com.example.spendwise

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface SpendWiseDestination {
    val route: String
    val icon: ImageVector
}

object Overview: SpendWiseDestination{
    override val route = "overview"
    override val icon = Icons.Filled.PieChart
}

object Expenses: SpendWiseDestination{
    override val route = "expenses"
    override val icon = Icons.Filled.AttachMoney
}

object Add: SpendWiseDestination{
    override val route = "add"
    override val icon = Icons.Filled.Add
}

object Detail: SpendWiseDestination{
    override val route = "detail"
    override val icon = Icons.Filled.Details
    const val detailArg = "detailArg"
    val routeWithArgs = "${route}/{${detailArg}}"
    val arguments = listOf(
        navArgument(detailArg) { type = NavType.StringType}
    )
}

object Edit: SpendWiseDestination{
    override val route = "edit"
    override val icon = Icons.Filled.Edit
    const val editArg = "editArg"
    val routeWithArgs = "${route}/{${editArg}}"
    val arguments = listOf(
        navArgument(editArg) { type = NavType.IntType}
    )
}

val SpendWiseTabRowScreens = listOf(Overview,Expenses, Add)