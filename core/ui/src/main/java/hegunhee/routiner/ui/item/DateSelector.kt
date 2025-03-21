package hegunhee.routiner.ui.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hegunhee.routiner.model.Date
import hegunhee.routiner.ui.R

@Composable
fun DateSelector(
    date: Date,
    onClickDate: (Date) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onClickDate(date) }
    ) {
        Text(date.year)
        Text("${date.month}/${date.dayOfMonth} (${date.dayOfWeek})")
        if (date.isSelected) {
            Icon(
                painter = painterResource(R.drawable.ic_dot_24),
                contentDescription = stringResource(R.string.selected_dot)
            )
        }
    }
}

@Preview
@Composable
private fun DateSelectorPreview() {
    DateSelector(
        date = Date(20250226, true),
        onClickDate = {}
    )
}
