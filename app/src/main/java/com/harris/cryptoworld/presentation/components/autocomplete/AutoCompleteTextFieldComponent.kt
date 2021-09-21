package com.harris.cryptoworld.presentation.components.autocomplete

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harris.cryptoworld.presentation.components.autocomplete.utils.AutoCompleteSearchBarTag
import com.harris.cryptoworld.presentation.components.autocomplete.utils.asAutoCompleteEntities
import com.harris.cryptoworld.presentation.components.searchbar.TextSearchBar
import timber.log.Timber
import java.util.*

@ExperimentalAnimationApi
@Composable
fun AutoCompleteValueSample(
    items: List<String>,
    onItemSelected: (name: String) -> Unit
) {
    val autoCompleteEntities = items.asAutoCompleteEntities(
        filter = { item, query ->
            item.lowercase(Locale.getDefault())
                .startsWith(query.lowercase(Locale.getDefault()))
        }
    )

    AutoCompleteBox(
        items = autoCompleteEntities,
        itemContent = { item ->
            ValueAutoCompleteItem(item.value)
        }
    ) {
        var value by remember { mutableStateOf("") }
        val view = LocalView.current

        onItemSelected { item ->
            value = item.value
            filter(value)
            view.clearFocus()
            onItemSelected(value)
        }

        TextSearchBar(
            modifier = Modifier.testTag(AutoCompleteSearchBarTag),
            value = value,
            label = "Currency Name",
            onDoneActionClick = {
                view.clearFocus()
            },
            onClearClick = {
                value = ""
                filter(value)
                view.clearFocus()
            },
            onFocusChanged = { focusState ->
                isSearching = focusState.isFocused
            },
            onValueChanged = { query ->
                value = query
                filter(value)
                Timber.d("search $query")
            }
        )
    }
}

@Composable
fun ValueAutoCompleteItem(item: String) {
    Column(
        modifier = Modifier
//            .fillMaxWidth()
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = item, style = MaterialTheme.typography.subtitle2)
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = false)
@Composable
fun prev() {
    AutoCompleteValueSample(listOf("a", "aa", "aaa")) {}
}