package com.tunombre.creaciondemuebles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tunombre.creaciondemuebles.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) showFragment(PerfilFragment())

        binding.btnMenuPerfil.setOnClickListener { showFragment(PerfilFragment(), hideMenu = true) }
        binding.btnMenuFotos.setOnClickListener { showFragment(FotosFragment()) }
        binding.btnMenuVideos.setOnClickListener { showFragment(VideosFragment()) }
        binding.btnMenuWeb.setOnClickListener { showFragment(WebFragment()) }
        binding.btnMenuBotones.setOnClickListener { showFragment(BotonesFragment()) }
    }

    fun showMainMenu() {
        binding.layoutMenuLateral.visibility = android.view.View.VISIBLE
        showFragment(PerfilFragment())
    }

    private fun showFragment(fragment: Fragment, hideMenu: Boolean = false) {
        binding.layoutMenuLateral.visibility =
            if (hideMenu) android.view.View.GONE else android.view.View.VISIBLE
        supportFragmentManager.beginTransaction()
            .replace(binding.contenedorFragmentos.id, fragment)
            .commit()
    }
}
