package hegunhee.routiner.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import routiner.core.model.Routine
import hegunhee.routiner.ui.R

@Composable
fun RecordRoutine(
    routine: Routine,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(10.dp)
    ) {
        if (routine.isFinished) {
            Icon(
                imageVector = Icons.Rounded.CheckCircle,
                contentDescription = stringResource(R.string.selected_category),
                tint = Color.Green,
            )
            Text(
                text = routine.text,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
            if (routine.category.isNotBlank()) {
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = routine.category,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun RecordRoutinePreview() {
    val routine = Routine(date = 20250320, text = "공부", isFinished = true, category = "카테고리")
    RecordRoutine(
        routine,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray)
    )
}
