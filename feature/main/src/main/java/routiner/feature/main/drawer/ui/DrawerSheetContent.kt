package routiner.feature.main.drawer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import routiner.feature.main.drawer.DrawerItem

@Composable
fun DrawerSheetContent(
    selectedDrawerItem: DrawerItem?,
    onClickDrawerItem: (DrawerItem) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        DrawerSheetHeader()
        DrawerItem.entries.forEach { drawerItem ->
            NavigationDrawerItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(38.dp),
                        painter = painterResource(id = drawerItem.iconRes),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                label = {
                    Text(drawerItem.titleString)
                },
                selected = selectedDrawerItem == drawerItem,
                onClick = {
                    onClickDrawerItem(drawerItem)
                },
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
}

@Composable
private fun DrawerSheetHeader() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        text = "Routiner",
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun DrawerSheetContentPreview() {
    DrawerSheetContent(
        selectedDrawerItem = null,
        onClickDrawerItem = { }
    )
}

@Preview
@Composable
private fun DrawerSheetHeaderPreview() {
    DrawerSheetHeader()
}
