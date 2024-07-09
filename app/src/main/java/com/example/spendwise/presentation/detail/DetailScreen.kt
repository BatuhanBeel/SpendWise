package com.example.spendwise.presentation.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.spendwise.R
import com.example.spendwise.domain.models.Spending
import com.example.spendwise.presentation.components.SpendingRow
import com.example.spendwise.presentation.components.CircleBody
import com.example.spendwise.ui.theme.SpendWiseTheme

@Composable
fun DetailScreen(
    argumentData: String,
    viewModel: DetailViewModel = hiltViewModel(),
    editItemOnClicked: (Int) -> Unit
    ) {

    LaunchedEffect(argumentData){
        viewModel.getAllItem(argumentData)
    }
    val data = viewModel.detailList

    Column(modifier = Modifier.fillMaxSize()) {
        CircleBody(
            items = data.toList(),
            circleLabel = "Total"
        )
        Text(
            text = stringResource(id = R.string.spendings),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        DetailBody(itemList = data, editItemOnClicked = editItemOnClicked)
    }
}

@Composable
fun DetailBody(
    itemList: List<Spending>,
    modifier: Modifier = Modifier,
    editItemOnClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(4.dp),
        state = rememberLazyListState()) {
        items(itemList, key = { item -> item.id }){
            SpendingRow(item = it, editable = true, editOnClick = editItemOnClicked)
        }
    }
}

@Preview(showBackground = true, showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun DetailScreenPreview() {
    SpendWiseTheme {
        Surface {
            DetailScreen(argumentData = "All", editItemOnClicked = { })
        }
    }
}