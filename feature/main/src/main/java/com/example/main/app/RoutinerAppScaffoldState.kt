package com.example.main.app

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.main.drawer.DrawerItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RoutinerAppScaffoldState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val drawerState: DrawerState
) {

    val currentDestination : State<NavBackStackEntry?>
        @Composable get() = navController.currentBackStackEntryAsState()

    fun navigate(drawerItem: DrawerItem) {
        navController.navigate(drawerItem.navRoute)
        coroutineScope.launch {
            drawerState.close()
        }
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    fun openDrawer() {
        coroutineScope.launch {
            drawerState.open()
        }
    }

    private var _selectedDrawerItem: MutableState<DrawerItem?> = mutableStateOf(null)
    val selectedDrawerItem get() = _selectedDrawerItem.value

    init {
        coroutineScope.launch {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                _selectedDrawerItem.value = destination.route?.let { DrawerItem.ofOrNull(it) }
            }
        }
    }
}
