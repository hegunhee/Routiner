package com.hegunhee.feature.base

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialog<T : ViewDataBinding>(@LayoutRes private val layoutResId : Int) : DialogFragment() {

    private var _binding: T? = null
    val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate<T>(inflater, layoutResId, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        context?.resizeDialog()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun Context.resizeDialog() {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        if(Build.VERSION.SDK_INT < 30){
            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = this@BaseDialog.dialog?.window

            val x = (size.x * WIDTH_PERCENT).toInt()
            val y = (size.y * HEIGHT_PERCENT).toInt()
            window?.setLayout(x,y)
        }else{
            val rect = windowManager.currentWindowMetrics.bounds

            val window = this@BaseDialog.dialog?.window

            val x = (rect.width() * WIDTH_PERCENT).toInt()
            val y = (rect.height() * HEIGHT_PERCENT).toInt()

            window?.setLayout(x,y)
        }
    }

    companion object {
        private const val WIDTH_PERCENT = 0.8
        private const val HEIGHT_PERCENT = 0.4
    }
}