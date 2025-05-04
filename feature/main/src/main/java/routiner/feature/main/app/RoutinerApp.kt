package routiner.feature.main.app

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import routiner.feature.main.drawer.ui.DrawerSheetContent
import routiner.feature.main.screen.navigation.MAIN_ROUTE
import routiner.feature.main.screen.navigation.mainNavGraph
import routiner.feature.daily.navigation.DAILY_ROUTE
import routiner.feature.daily.navigation.dailyNavGraph
import routiner.feature.record.navigation.recordNavGraph
import com.hegunhee.repeat.navigation.repeatRoutineNavGraph
import routiner.feature.insertRoutine.navigation.INSERT_REPEAT_ROUTINE_ROUTE
import routiner.feature.insertRoutine.navigation.INSERT_ROUTINE_ROUTE
import routiner.feature.insertRoutine.navigation.insertNavGraph
import com.hegunhee.setting.navigation.settingNavGraph
import kotlinx.coroutines.CoroutineScope

@Composable
fun RoutinerApp(
    routinerAppScaffoldState: RoutinerAppScaffoldState = rememberRoutinerAppScaffoldState()
) {
    RoutinerAppDrawer(
        routinerAppScaffoldState = routinerAppScaffoldState,
        drawerSheetContent = {
            DrawerSheetContent(
                routinerAppScaffoldState.selectedDrawerItem,
                routinerAppScaffoldState::navigate
            )
        }
    ) {
        NavHost(
            navController = routinerAppScaffoldState.navController,
            startDestination = MAIN_ROUTE
        ) {
            mainNavGraph(
                successRoute = DAILY_ROUTE,
                onNavigateTo = routinerAppScaffoldState::navigateMainTo,
            )
            dailyNavGraph(
                onClickDrawerButton = routinerAppScaffoldState::openDrawer,
                onClickAddRoutine = routinerAppScaffoldState::navigateInsertRoutine,
            )
            insertNavGraph(
                onClickBackButton = routinerAppScaffoldState::popBackStack
            )
            recordNavGraph(
                onClickDrawer = routinerAppScaffoldState::openDrawer
            )
            repeatRoutineNavGraph(
                onClickDrawer = routinerAppScaffoldState::openDrawer,
                onClickAddRepeatRoutine = routinerAppScaffoldState::navigateInsertRepeatRoutine,
            )
            settingNavGraph(
                onClickDrawer = routinerAppScaffoldState::openDrawer
            )
        }
    }
}


@Composable
fun rememberRoutinerAppScaffoldState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
): RoutinerAppScaffoldState {
    return remember(navController, coroutineScope, drawerState) {
        RoutinerAppScaffoldState(
            navController = navController,
            coroutineScope = coroutineScope,
            drawerState = drawerState
        )
    }
}

@Composable
fun RoutinerAppDrawer(
    routinerAppScaffoldState: RoutinerAppScaffoldState = rememberRoutinerAppScaffoldState(),
    drawerSheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit
) {
    val isDrawerEnable = !disableDrawerRoutes.contains(routinerAppScaffoldState.currentDestination.value?.destination?.route.toString())

    ModalNavigationDrawer(
        drawerState = routinerAppScaffoldState.drawerState,
        drawerContent = {
            ModalDrawerSheet {
                drawerSheetContent()
            }
        },
        gesturesEnabled = isDrawerEnable
    ) {
        content()
    }
}

private val disableDrawerRoutes = listOf(MAIN_ROUTE, INSERT_ROUTINE_ROUTE, INSERT_REPEAT_ROUTINE_ROUTE)
