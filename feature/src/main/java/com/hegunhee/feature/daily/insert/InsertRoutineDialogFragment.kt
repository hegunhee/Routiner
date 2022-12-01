package com.hegunhee.feature.daily.insert

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hegunhee.feature.R
import com.hegunhee.feature.base.BaseDialog
import com.hegunhee.feature.databinding.DialogDailyRoutineBinding
import kotlinx.coroutines.launch

class InsertRoutineDialogFragment : BaseDialog<DialogDailyRoutineBinding>(R.layout.dialog_daily_routine){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}