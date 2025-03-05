package com.example.main

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
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.main.drawer.ui.DrawerSheetContent
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
            startDestination = TestRoute
        ) {
            composable(route = TestRoute) {
                TestScreen()
            }
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
    ModalNavigationDrawer(
        drawerState = routinerAppScaffoldState.drawerState,
        drawerContent = {
            ModalDrawerSheet {
                drawerSheetContent()
            }
        }
    ) {
        content()
    }
}
