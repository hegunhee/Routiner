package routiner.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import routiner.core.model.RepeatRoutine
import routiner.core.ui.R

@Composable
fun RepeatRoutineItem(
    repeatRoutine: RepeatRoutine,
    onClickRepeatRoutineDelete : (String) -> Unit,
    modifier : Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(10.dp),
    ) {
        Row {
           Text(
               repeatRoutine.text,
               fontSize = 20.sp,
               fontWeight = FontWeight.Bold,
               modifier = Modifier.alignByBaseline(),
           )
           Spacer(modifier = Modifier.weight(1f))
           IconButton({onClickRepeatRoutineDelete(repeatRoutine.text)}) {
               Icon(
                   imageVector = Icons.Rounded.Close,
                   contentDescription = stringResource(R.string.repeat_routine_delete_button_desc),
                   tint = Color.Red,
               )
           }
        }
        Text(stringResource(R.string.day_of_week, repeatRoutine.dayOfWeekList))
        Text(stringResource(R.string.repeat_routine_category, repeatRoutine.category))
    }
}

@Preview
@Composable
private fun RepeatRoutineItemPreview() {
    val repeatRoutine = RepeatRoutine(text = "반복루틴", dayOfWeekList = listOf("월","화","목"), category = "카테고리")
    RepeatRoutineItem(
        repeatRoutine = repeatRoutine,
        onClickRepeatRoutineDelete = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray)

    )
}
