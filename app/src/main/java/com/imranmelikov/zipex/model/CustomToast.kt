package com.imranmelikov.zipex.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.imranmelikov.zipex.R

class CustomToast(private val context: Context) {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val layout: View = inflater.inflate(R.layout.toast, null)
    private val toast: Toast = Toast(context)

    init {
//        // Arka plan rengini değiştirme
//        layout.setBackgroundColor(context.resources.getColor(R.color.toast))
//
//        // Metin rengini değiştirme
//        val messageTextView = layout.findViewById<TextView>(R.id.custom_toast_message)
//        messageTextView.setTextColor(context.resources.getColor(R.color.white))
//
        toast.view = layout
        toast.duration = Toast.LENGTH_SHORT
    }

    fun showToast(message: String) {
        val messageTextView = layout.findViewById<TextView>(R.id.custom_toast_message)
        messageTextView.text = message
        toast.show()
    }
}
