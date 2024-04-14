package com.hegunhee.setting

import android.app.AlarmManager
import android.content.Context
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
import com.hegunhee.routiner.util.Time
import com.hegunhee.setting.alarm.AlarmActions
import com.hegunhee.setting.alarm.AlarmReceiver
import com.hegunhee.setting.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val viewModel : SettingViewModel by viewModels()

    private val alarmManager : AlarmManager by lazy {
        requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }
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
                    viewModel.alarmTime.collect { alarmTime ->
                        binding.hourSpinner.setSelection(Alarm.getHourList().indexOf(alarmTime.hour))
                        binding.minuteSpinner.setSelection(Alarm.getMinuteList().indexOf(alarmTime.minute))
                    }
                }
                launch {
                    viewModel.alarmActions.collect { Actions ->
                        when(Actions) {
                            is AlarmActions.Register -> {
                                registerAlarm(Actions.hour.toInt(),Actions.minute.toInt())
                            }
                            AlarmActions.Cancel ->  {
                                cancelAlarm()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun registerAlarm(hour : Int,minute : Int) {
        val pendingIntent = AlarmReceiver.getAlarmPendingIntent(requireContext(),AlarmReceiver.DAILY_ALARM_PENDING_INTENT_FLAG)
        Toast.makeText(requireContext(), "${hour}시 ${minute}분에 알람이 지정되었습니다.", Toast.LENGTH_SHORT).show()
        alarmManager.setRepeating(AlarmManager.RTC,Time.toTimeMills(hour = hour,minute = minute),AlarmManager.INTERVAL_DAY,pendingIntent)
    }

    private fun cancelAlarm() {
        val pendingIntent = AlarmReceiver.getAlarmPendingIntent(requireContext(),AlarmReceiver.DAILY_ALARM_PENDING_INTENT_FLAG)
        alarmManager.cancel(pendingIntent)
        Toast.makeText(requireContext(), "알람이 취소되었습니다.", Toast.LENGTH_SHORT).show()
    }
}