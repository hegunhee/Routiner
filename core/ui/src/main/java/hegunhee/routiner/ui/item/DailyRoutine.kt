package hegunhee.routiner.ui.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import routiner.core.model.Routine
import hegunhee.routiner.ui.R

@Composable
fun DailyRoutine(
    routine: Routine,
    onClickRoutine: (Routine) -> Unit,
    onClickDeleteRoutine: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClickRoutine(routine) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = routine.isFinished,
            onCheckedChange = { onClickRoutine(routine) }
        )
        Spacer(
            modifier = modifier.padding(start = 20.dp)
        )
        Column {
            Text(
                text = routine.text,
                fontSize = 20.sp,
            )
            if(routine.category.isNotBlank()) {
                Text(text = routine.category,modifier = Modifier.padding(start = 10.dp))
            }
        }

        Spacer(
            modifier = modifier.weight(1f)
        )
        Button({ onClickDeleteRoutine(routine.id) }) {
            Text(stringResource(R.string.delete_routine))
        }
    }
}

@Preview
@Composable
fun DailyRoutinePreview() {
    var isFinished by remember { mutableStateOf(false) }
    DailyRoutine(
        routine = Routine(20250319, "테스트루틴입니다.", isFinished = isFinished),
        onClickRoutine = { isFinished = !isFinished },
        onClickDeleteRoutine = {},
    )
}
