package com.tunombre.creaciondemuebles

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FotosFragment : Fragment() {
    private val images = listOf(
        android.R.drawable.ic_menu_gallery,
        android.R.drawable.ic_menu_gallery,
        android.R.drawable.ic_menu_gallery,
        android.R.drawable.ic_menu_gallery
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_fotos, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val grid = view.findViewById<GridView>(R.id.grid_galeria)
        grid.adapter = GalleryAdapter()
        grid.setOnItemClickListener { _, _, position, _ -> showImageDialog(images[position]) }

        view.findViewById<Button>(R.id.btn_devolver_fotos).setOnClickListener {
            (activity as? MainActivity)?.showMainMenu()
        }
    }

    private fun showImageDialog(resourceId: Int) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialogo_imagen)

        val image = dialog.findViewById<ImageView>(R.id.imagen_zoom)
        var scale = 1f

        image.setImageResource(resourceId)

        dialog.findViewById<Button>(R.id.btn_imagen_mas).setOnClickListener {
            scale = (scale * 1.25f).coerceAtMost(4f)
            image.scaleX = scale
            image.scaleY = scale
        }

        dialog.findViewById<Button>(R.id.btn_imagen_menos).setOnClickListener {
            scale = (scale * 0.8f).coerceAtLeast(1f)
            image.scaleX = scale
            image.scaleY = scale
        }

        dialog.findViewById<Button>(R.id.btn_cerrar_imagen).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    private inner class GalleryAdapter : BaseAdapter() {
        override fun getCount() = images.size
        override fun getItem(position: Int) = images[position]
        override fun getItemId(position: Int) = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val imageView = (convertView as? ImageView) ?: ImageView(requireContext()).apply {
                layoutParams = GridView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    260
                )
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                setPadding(8, 8, 8, 8)
            }
            imageView.setImageResource(images[position])
            return imageView
        }
    }
}
