package com.example.spendwise.presentation.overview

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.spendwise.R
import com.example.spendwise.domain.models.Spending
import com.example.spendwise.presentation.components.SeeAllButton
import com.example.spendwise.presentation.components.SpendingRow
import com.example.spendwise.presentation.overview.components.OverviewCard
import com.example.spendwise.ui.theme.SpendWiseTheme

private const val OVERVIEW_MAX_VISIBLE_DATA_COUNT = 5

@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    onClickSeeAll: () -> Unit
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.fetchInitialData()
    }

    val data = viewModel.overviewState.value

    OverviewBody(
        modifier = Modifier.semantics { contentDescription = "Overview Screen" },
        dataList = data.spendingList,
        balance = data.userAmountPreferences,
        onClickSeeAll = onClickSeeAll
    ){
        SpendingRow(item = it, editable = false, editOnClick = { })
    }
}

@Composable
fun OverviewBody(
    modifier: Modifier = Modifier,
    dataList: List<Spending>,
    balance: Float,
    onClickSeeAll: () -> Unit,
    rows: @Composable (Spending) -> Unit,
) {
    val expenses = remember(dataList) {
        dataList.map { it.amount }.sum()
    }

    Column(modifier = modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
    ) {
        OverviewCard(balance = balance, expenses = expenses)
        Text(
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.all_transactions),
            style = MaterialTheme.typography.titleLarge
        )
        Column {
            dataList.take(OVERVIEW_MAX_VISIBLE_DATA_COUNT).forEach {
                rows(it)
            }
            if (OVERVIEW_MAX_VISIBLE_DATA_COUNT < dataList.size){
                SeeAllButton(onClick = onClickSeeAll)
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OverviewScreenPreview() {
    SpendWiseTheme {
        Surface(Modifier.fillMaxSize()) {
            OverviewScreen(
                onClickSeeAll = { }
            )
        }
    }
}