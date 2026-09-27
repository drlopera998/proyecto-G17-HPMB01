package com.tunombre.creaciondemuebles

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.VideoView
import androidx.fragment.app.Fragment

class VideosFragment : Fragment() {
    // Agregar aquí las rutas/URLs de los videos definidos por el equipo.
    private val videoUrls = listOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_videos, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list = view.findViewById<ListView>(R.id.lista_videos)
        val playerContainer = view.findViewById<ViewGroup>(R.id.contenedor_reproductor)

        val titles = if (videoUrls.isEmpty()) {
            listOf("Tutoriales pendientes de agregar")
        } else {
            videoUrls.indices.map { "Tutorial " + (it + 1) }
        }
        list.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, titles)

        list.setOnItemClickListener { _, _, position, _ ->
            if (position >= videoUrls.size) return@setOnItemClickListener
            playerContainer.removeAllViews()
            val player = VideoView(requireContext())
            player.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            player.setVideoURI(Uri.parse(videoUrls[position]))
            player.setOnPreparedListener { it.start() }
            playerContainer.addView(player)
            playerContainer.visibility = View.VISIBLE
        }

        view.findViewById<Button>(R.id.btn_devolver_videos).setOnClickListener {
            stopPlayer(playerContainer)
            (activity as? MainActivity)?.showMainMenu()
        }
    }

    override fun onPause() {
        view?.findViewById<ViewGroup>(R.id.contenedor_reproductor)?.let(::stopPlayer)
        super.onPause()
    }

    private fun stopPlayer(container: ViewGroup) {
        (container.getChildAt(0) as? VideoView)?.stopPlayback()
        container.removeAllViews()
        container.visibility = View.GONE
    }
}
