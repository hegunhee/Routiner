package com.hegunhee.feature.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hegunhee.feature.R
import com.hegunhee.feature.databinding.ActivityMainBinding
import com.hegunhee.feature.databinding.DialogFirstOpenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewmodel = viewModel
            lifecycleOwner = this@MainActivity
        }
        setContentView(binding.root)
        initActionBar()
        setNavigation()
        initObserver()
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

    private fun initObserver() = viewModel.firstAppOpenEvent.observe(this){
        when(it){
            FirstAppOpenEvent.UnInitalized -> {}
            FirstAppOpenEvent.OpenDialog -> {
                openDialog()
                viewModel.setEventFinish()
            }
            FirstAppOpenEvent.Finished -> {}
        }
    }

    private fun openDialog() {
        val customDialogBinding = DialogFirstOpenBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this).setView(customDialogBinding.root).show()
        customDialogBinding.alertAcceptButton.setOnClickListener {
            val isChecked = customDialogBinding.alertSwitch.isChecked
            viewModel.setInitNotiValue(isChecked)
            Toast.makeText(this@MainActivity, "알람 설정이 ${if(isChecked) "승인" else "해제"} 되었습니다.", Toast.LENGTH_SHORT).show()
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