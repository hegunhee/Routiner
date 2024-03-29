package com.example.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.main.databinding.ActivityMainBinding
import com.example.main.guide.GuideDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // FIXME : 다크모드 정식 오픈시 해당 코드 제거
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            this.viewmodel = viewModel
        }
        setContentView(binding.root)
        if(viewModel.isAppFirstOpen() && !viewModel.isGuideDialogOpen()) {
            openGuideDialog()
        }
        initActionBar()
        initNavigation()
    }

    private fun initActionBar() = with(binding) {
        setSupportActionBar(toolBar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_dehaze)
        }
    }

    private fun initNavigation() = with(binding) {
        (supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment).let {
            val navController = it.navController
            navView.setupWithNavController(navController)
        }
    }

    private fun openGuideDialog() {
        GuideDialogFragment.getInstance().show(supportFragmentManager,GuideDialogFragment.TAG)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}