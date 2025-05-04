package routiner.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import routiner.core.ui.R

@Composable
fun SelectableDayOfWeek(
    dayOfWeekText : String,
    isDayOfWeekSelected : Boolean,
    onClickDayOfWeek: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .widthIn(min = 50.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClickDayOfWeek(dayOfWeekText) }
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if(isDayOfWeekSelected) {
            Icon(
                imageVector = Icons.Rounded.CheckCircle,
                contentDescription = stringResource(
                    R.string.selected_day_of_week_desc,
                    dayOfWeekText
                ),
                tint = Color.Green
            )
        }
        Text(
            text = dayOfWeekText,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 4.dp)
                .clickable { onClickDayOfWeek(dayOfWeekText) }
        )
    }
}

@Preview
@Composable
private fun SelectableDayOfWeekPreview() {
    var isDayOfWeekClicked by remember { mutableStateOf(false) }
    SelectableDayOfWeek(
        dayOfWeekText = "월",
        isDayOfWeekSelected = isDayOfWeekClicked,
        onClickDayOfWeek = { _ ->
            isDayOfWeekClicked = !isDayOfWeekClicked
        },
        modifier = Modifier
            .height(60.dp)
            .background(Color.Gray)
            .wrapContentHeight()
    )
}
