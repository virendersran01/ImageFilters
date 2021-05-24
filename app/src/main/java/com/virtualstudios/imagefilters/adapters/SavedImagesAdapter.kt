package com.virtualstudios.imagefilters.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.virtualstudios.imagefilters.databinding.ItemContainerSavedImageBinding
import com.virtualstudios.imagefilters.listeners.SavedImageListener
import java.io.File

class SavedImagesAdapter(
    private val savedImages: List<Pair<File, Bitmap>>,
    private val savedImageListener: SavedImageListener
) : RecyclerView.Adapter<SavedImagesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemContainerSavedImageBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemContainerSavedImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(savedImages[position]){
                binding.imageSaved.setImageBitmap(second)
                binding.imageSaved.setOnClickListener { savedImageListener.onImageClick(first) }
            }
        }
    }

    override fun getItemCount() = savedImages.size
}