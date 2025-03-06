package com.example.main

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.example.main.drawer.DrawerItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RoutinerAppScaffoldState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val drawerState: DrawerState
) {
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
