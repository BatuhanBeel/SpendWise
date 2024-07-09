package com.example.spendwise.presentation.expenses

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.CrueltyFree
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Train
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.spendwise.ui.theme.*
import com.example.spendwise.R
import com.example.spendwise.domain.models.Category
import com.example.spendwise.domain.models.Spending
import com.example.spendwise.presentation.components.SpendingRow
import com.example.spendwise.presentation.components.CircleBody
import com.example.spendwise.presentation.expenses.components.CategoryCard
import com.example.spendwise.presentation.components.SpendWiseDialog
import com.example.spendwise.ui.theme.SpendWiseTheme

@Composable
fun ExpensesScreen(
    viewModel: ExpensesViewModel = hiltViewModel(),
    onClickSeeAll: (String) -> Unit,
    onItemEditClicked: (Int) -> Unit
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.getAllItem()
    }
    val data by viewModel.expensesState

    val alertDialogIsVisible = remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        TextButton(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 24.dp),
            onClick = {
                alertDialogIsVisible.value = true
            }
        ) {
            Text(
                text = stringResource(id = R.string.reset_all),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        CircleBody(
            items = data.allExpensesList,
            circleLabel = stringResource(id = R.string.total)
        )
        ExpensesBody(
            data = data.allExpensesList,
            onClickSeeAll = onClickSeeAll,
            onItemEditClicked = onItemEditClicked
        )
    }
    if (alertDialogIsVisible.value) {
        SpendWiseDialog(
            title = stringResource(id = R.string.delete_all),
            text = "Do you want to delete all expending?",
            icon = Icons.Filled.Clear,
            onDismissRequest = { alertDialogIsVisible.value = false },
            onConfirmation = {
                alertDialogIsVisible.value = false
                viewModel.clearAllItem()
            }
        )
    }
}

@Composable
fun ExpensesBody(
    data: List<Spending>,
    onClickSeeAll: (String) -> Unit,
    onItemEditClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val foodList = remember(data) {
        mutableStateListOf<Spending>().apply {
            addAll(data.filter { it.category == Category.Food.name })
        }
    }
    val shoppingList = remember(data) {
        mutableStateListOf<Spending>().apply {
            addAll(data.filter { it.category == Category.Shopping.name })
        }
    }
    val entertainmentList = remember(data) {
        mutableStateListOf<Spending>().apply {
            addAll(data.filter { it.category == Category.Entertainment.name })
        }
    }
    val healthList = remember(data) {
        mutableStateListOf<Spending>().apply {
            addAll(data.filter { it.category == Category.Health.name })
        }
    }
    val travelList = remember(data) {
        mutableStateListOf<Spending>().apply {
            addAll(data.filter { it.category == Category.Travel.name })
        }
    }
    val otherList = remember(data) {
        mutableStateListOf<Spending>().apply {
            addAll(data.filter { it.category == Category.Other.name })
        }
    }

    Column(
        modifier = modifier
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FoodCard(
            foodList,
            onClickSeeAll,
            onItemEditClicked = onItemEditClicked
        )
        ShoppingCard(
            shoppingList,
            onClickSeeAll,
            onItemEditClicked = onItemEditClicked
        )
        EntertainmentCard(
            entertainmentList,
            onClickSeeAll,
            onItemEditClicked = onItemEditClicked
        )
        HealthCard(
            healthList,
            onClickSeeAll,
            onItemEditClicked = onItemEditClicked
        )
        TravelCard(
            travelList,
            onClickSeeAll,
            onItemEditClicked = onItemEditClicked
        )
        OtherCard(
            otherList,
            onClickSeeAll,
            onItemEditClicked = onItemEditClicked
        )
    }
}

@Composable
fun FoodCard(
    data: List<Spending>,
    onClickSeeAll: (String) -> Unit,
    onItemEditClicked: (Int) -> Unit
) {
    val amount = remember(data) {
        data.map { it.amount }.sum()
    }
    CategoryCard(
        title = stringResource(id = R.string.food),
        color = foodCategory,
        icon = Icons.Filled.Fastfood,
        amount = amount,
        onClickSeeAll = { onClickSeeAll(Category.Food.name) },
        data = data
    ) {
        SpendingRow(
            item = it,
            editable = true,
            editOnClick = onItemEditClicked
        )
    }
}

@Composable
fun ShoppingCard(
    data: List<Spending>,
    onClickSeeAll: (String) -> Unit,
    onItemEditClicked: (Int) -> Unit
) {
    val amount = remember(data) {
        data.map { it.amount }.sum()
    }
    CategoryCard(
        title = stringResource(id = R.string.shopping),
        color = shoppingCategory,
        icon = Icons.Filled.ShoppingCart,
        amount = amount,
        onClickSeeAll = { onClickSeeAll(Category.Shopping.name) },
        data = data
    ) {
        SpendingRow(
            item = it,
            editable = true,
            editOnClick = onItemEditClicked
        )
    }
}

@Composable
fun EntertainmentCard(
    data: List<Spending>,
    onClickSeeAll: (String) -> Unit,
    onItemEditClicked: (Int) -> Unit
) {
    val amount = remember(data) {
        data.map { it.amount }.sum()
    }
    CategoryCard(
        title = stringResource(id = R.string.entertainment),
        color = entertainmentCategory,
        icon = Icons.Filled.CrueltyFree,
        amount = amount,
        onClickSeeAll = { onClickSeeAll(Category.Entertainment.name) },
        data = data
    ) {
        SpendingRow(
            item = it,
            editable = true,
            editOnClick = onItemEditClicked
        )
    }
}

@Composable
fun HealthCard(
    data: List<Spending>,
    onClickSeeAll: (String) -> Unit,
    onItemEditClicked: (Int) -> Unit
) {
    val amount = remember(data) {
        data.map { it.amount }.sum()
    }
    CategoryCard(
        title = stringResource(id = R.string.health),
        color = healthCategory,
        icon = Icons.Filled.Healing,
        amount = amount,
        onClickSeeAll = { onClickSeeAll(Category.Health.name) },
        data = data
    ) {
        SpendingRow(
            item = it,
            editable = true,
            editOnClick = onItemEditClicked
        )
    }
}

@Composable
fun TravelCard(
    data: List<Spending>,
    onClickSeeAll: (String) -> Unit,
    onItemEditClicked: (Int) -> Unit
) {
    val amount = remember(data) {
        data.map { it.amount }.sum()
    }
    CategoryCard(
        title = stringResource(id = R.string.travel),
        color = travelCategory,
        icon = Icons.Filled.Train,
        amount = amount,
        onClickSeeAll = { onClickSeeAll(Category.Travel.name) },
        data = data
    ) {
        SpendingRow(
            item = it,
            editable = true,
            editOnClick = onItemEditClicked
        )
    }
}

@Composable
fun OtherCard(
    data: List<Spending>,
    onClickSeeAll: (String) -> Unit,
    onItemEditClicked: (Int) -> Unit
) {
    val amount = remember(data) {
        data.map { it.amount }.sum()
    }
    CategoryCard(
        title = stringResource(id = R.string.other),
        color = otherCategory,
        icon = Icons.Filled.MoreHoriz,
        amount = amount,
        onClickSeeAll = { onClickSeeAll(Category.Other.name) },
        data = data
    ) {
        SpendingRow(
            item = it,
            editable = true,
            editOnClick = onItemEditClicked
        )
    }
}

@Preview(showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ExpensesScreenPreview() {
    SpendWiseTheme {
        Surface(Modifier.fillMaxSize()) {
            ExpensesScreen(
                onClickSeeAll = { },
                onItemEditClicked = { }
            )
        }
    }
}