package com.imranmelikov.zipex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.adapter.RegularOrderAdapter
import com.imranmelikov.zipex.databinding.ActivityWebViewBinding
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding:ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWebViewBinding.inflate(layoutInflater)
       setContentView(binding.root)

        binding.back.setOnClickListener {
finish()
        }
        binding.myWebView.webViewClient = WebViewClient()
        binding.myWebView.settings.javaScriptEnabled = true

        val intent = intent
        val message = intent.getStringExtra("key")
        val cartMessage=intent.getStringExtra("keyCart")

        if (message != null) {
            binding.myWebView.loadUrl(message)
            binding.textview.text=message
        } else if (cartMessage != null) {
            binding.myWebView.loadUrl(cartMessage)
            binding.textview.text=cartMessage
        } else {

        }
    }
}