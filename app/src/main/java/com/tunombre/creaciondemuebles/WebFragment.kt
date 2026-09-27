package com.tunombre.creaciondemuebles

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class WebFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_web, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val field = view.findViewById<EditText>(R.id.campo_direccion_web)
        val web = view.findViewById<WebView>(R.id.vista_web)

        web.settings.javaScriptEnabled = true
        web.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                field.setText(url.orEmpty())
            }
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?) = false
        }

        view.findViewById<Button>(R.id.btn_cargar_web).setOnClickListener {
            var url = field.text.toString().trim()
            if (url.isNotEmpty()) {
                if (!url.startsWith("http://") && !url.startsWith("https://")) url = "https://$url"
                web.loadUrl(url)
            }
        }
        view.findViewById<Button>(R.id.btn_devolver_web).setOnClickListener {
            web.stopLoading()
            (activity as? MainActivity)?.showMainMenu()
        }
    }
}
