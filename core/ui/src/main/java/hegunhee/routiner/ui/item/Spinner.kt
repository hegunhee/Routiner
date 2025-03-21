package hegunhee.routiner.ui.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hegunhee.routiner.ui.R

@Composable
fun Spinner(
    items: List<*>,
    selectedItem: String,
    onClickItem: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (isExpanded, onExpandedChanged) = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.clickable { onExpandedChanged(true) }
    ) {
        Text(selectedItem, modifier = modifier.alignByBaseline())
        Icon(
            imageVector = Icons.Rounded.ArrowDropDown,
            contentDescription = stringResource(R.string.spinner_down_icon),
            modifier = modifier.alignByBaseline()
        )
    }

    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = { onExpandedChanged(false) }
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text = { Text(item.toString()) },
                onClick = {
                    onClickItem(item.toString())
                    onExpandedChanged(false)
                }
            )
        }
    }
}

@Preview
@Composable
private fun SpinnerPreview() {
    var items by remember { mutableStateOf(listOf("01", "02", "03")) }
    var selectedItem by remember { mutableStateOf(items[0]) }

    val onSelectItemChanged: (String) -> Unit = { newItem ->
        selectedItem = newItem
    }

    Spinner(
        items,
        selectedItem,
        onSelectItemChanged,
    )
}
