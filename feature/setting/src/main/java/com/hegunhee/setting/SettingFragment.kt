package com.hegunhee.setting

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hegunhee.common.base.BaseFragment
import com.hegunhee.routiner.util.Alarm
import com.hegunhee.setting.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val viewModel : SettingViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            this.viewModel = this@SettingFragment.viewModel
        }
        setActionBarTitle()
        setTimeSpinner()
        observeData()
    }

    private fun setActionBarTitle(){
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "설정"
    }

    private fun setTimeSpinner() {
        binding.run {
            hourSpinner.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item,Alarm.getHourList()).apply {
                setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            }
            minuteSpinner.adapter =  ArrayAdapter(requireContext(), R.layout.spinner_item,Alarm.getMinuteList()).apply {
                setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            }
            hourSpinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    this@SettingFragment.viewModel.setAlarmHour(Alarm.getHourList()[position] )
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
            minuteSpinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    this@SettingFragment.viewModel.setAlarmMinute(Alarm.getMinuteList()[position])
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.toastMessage.collect{ message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
                launch {
                    viewModel.alarmTime.collect { alarmTime ->
                        binding.hourSpinner.setSelection(Alarm.getHourList().indexOf(alarmTime.hour))
                        binding.minuteSpinner.setSelection(Alarm.getMinuteList().indexOf(alarmTime.minute))
                    }
                }
            }
        }
    }
}