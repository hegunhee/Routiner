package routiner.feature.insertRoutine.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import routiner.core.model.Category
import routiner.core.ui.component.SelectableCategory

@Composable
internal fun CategoryLazyList(
    items: List<Category>,
    onClickCategory: (String) -> Unit,
    onClickCategoryDelete: (String) -> Unit,
    modifier: Modifier = Modifier,
    rows: Int = 3,
) {
    val categoryModifier = modifier
        .height(60.dp)
        .background(Color.Gray)
        .wrapContentHeight()

    LazyHorizontalGrid(
        rows = GridCells.Fixed(rows),
        modifier = modifier.height(180.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) {
            SelectableCategory(
                categoryText = it.name,
                isCategoryClicked = it.isSelected,
                onCategoryClick = onClickCategory,
                onCategoryDeleteClick = onClickCategoryDelete,
                modifier = categoryModifier,
            )
        }
    }
}

@Preview
@Composable
private fun CategoryLazyListPreview() {
    var list by remember {
        mutableStateOf(
            listOf<Category>(
                Category("카테고리1", false),
                Category("카테고리2", isSelected = false),
                Category("카테고리3", isSelected = false)
            )
        )
    }

    val onCategoryClick: (String) -> Unit = { newCategory ->
        list = list.map {
            if (it.name == newCategory) it.copy(isSelected = !it.isSelected)
            else if (it.isSelected) it.copy(isSelected = false)
            else it
        }
    }
    val onCategoryDelete: (String) -> Unit = { deletedCategory ->
        list = list.filter { it.name != deletedCategory }
    }
    CategoryLazyList(
        items = list,
        onClickCategory = onCategoryClick,
        onClickCategoryDelete = onCategoryDelete,
    )
}
