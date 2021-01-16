package com.usin.chabongmilktime

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.usin.chabongmilktime.databinding.PopupWindowBinding


//https://johncodeos.com/how-to-create-a-popup-window-in-android-using-kotlin/
class PopUpWindow(context: Context) : Dialog(context) {

    // Binding object instance with access to the views in the popup_window.xml layout
    private lateinit var binding: PopupWindowBinding

    init {setCancelable(true)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = PopupWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOk.setOnClickListener {
        }

        binding.btnCancel.setOnClickListener { dismiss() }
    }
}