package com.tunombre.creaciondemuebles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class PerfilFragment : Fragment() {
    private var textSize = 16f

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_perfil, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val text = view.findViewById<TextView>(R.id.txt_informacion)
        view.findViewById<Button>(R.id.btn_agrandar_texto).setOnClickListener {
            textSize += 2f
            text.textSize = textSize
        }
        view.findViewById<Button>(R.id.btn_reducir_texto).setOnClickListener {
            textSize = (textSize - 2f).coerceAtLeast(8f)
            text.textSize = textSize
        }
        view.findViewById<Button>(R.id.btn_devolver_perfil).setOnClickListener {
            (activity as? MainActivity)?.showMainMenu()
        }
    }
}
