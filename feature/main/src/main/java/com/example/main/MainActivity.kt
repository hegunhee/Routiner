package com.example.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.main.databinding.ActivityMainBinding
import com.example.main.databinding.DialogFirstOpenBinding
import com.example.main.databinding.DialogGuideBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            this.viewmodel = viewModel
        }
        setContentView(binding.root)
        if(viewModel.isAppFirstOpen()){
            openGuideDialog()
        }
        initActionBar()
        setNavigation()
    }

    private fun initActionBar() = with(binding) {
        setSupportActionBar(toolBar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_dehaze)
        }
    }

    private fun setNavigation() = with(binding) {
        (supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment).let {
            val navController = it.navController
            navView.setupWithNavController(navController)
        }
    }

    private fun openGuideDialog() {
        val customDialogBinding = DialogGuideBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this).setView(customDialogBinding.root).show()
        dialog.setCancelable(false)
        customDialogBinding.alertAcceptButton.setOnClickListener {
            val isChecked = customDialogBinding.alertSwitch.isChecked
            viewModel.setAppFirstOpened()
            Toast.makeText(this@MainActivity, getString(R.string.notification_setting,if(isChecked) "승인" else "해제"), Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(Gravity.LEFT)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isOpen) {
            binding.drawerLayout.close()
        } else {
            finish()
        }
    }
}