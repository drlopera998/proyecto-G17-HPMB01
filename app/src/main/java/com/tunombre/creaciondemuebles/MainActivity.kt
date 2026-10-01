package com.tunombre.creaciondemuebles

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tunombre.creaciondemuebles.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var menuButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        menuButtons = listOf(
            binding.btnMenuPerfil,
            binding.btnMenuFotos,
            binding.btnMenuVideos,
            binding.btnMenuWeb,
            binding.btnMenuBotones
        )

        binding.btnMenuPerfil.setOnClickListener { showFragment(PerfilFragment()) }
        binding.btnMenuFotos.setOnClickListener { showFragment(FotosFragment()) }
        binding.btnMenuVideos.setOnClickListener { showFragment(VideosFragment()) }
        binding.btnMenuWeb.setOnClickListener { showFragment(WebFragment()) }
        binding.btnMenuBotones.setOnClickListener { showFragment(BotonesFragment()) }

        if (savedInstanceState == null) {
            showFragment(PerfilFragment())
        }
    }

    fun showMainMenu() {
        showFragment(PerfilFragment())
    }

    private fun showFragment(fragment: Fragment) {
        binding.layoutMenuLateral.visibility = View.VISIBLE

        val selectedButton = when (fragment) {
            is PerfilFragment -> binding.btnMenuPerfil
            is FotosFragment -> binding.btnMenuFotos
            is VideosFragment -> binding.btnMenuVideos
            is WebFragment -> binding.btnMenuWeb
            is BotonesFragment -> binding.btnMenuBotones
            else -> null
        }

        menuButtons.forEach { button ->
            val selected = button == selectedButton
            button.setBackgroundResource(
                if (selected) R.drawable.bg_menu_button_active
                else R.drawable.bg_menu_button
            )
            button.setTextColor(
                if (selected) android.graphics.Color.WHITE
                else getColor(R.color.text_dark)
            )
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.contenedorFragmentos.id, fragment)
            .commit()
    }
}
