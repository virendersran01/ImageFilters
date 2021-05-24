package com.virtualstudios.imagefilters.activities.savedimages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import com.virtualstudios.imagefilters.activities.editimage.EditImageActivity
import com.virtualstudios.imagefilters.activities.filteredimage.FilteredImageActivity
import com.virtualstudios.imagefilters.adapters.SavedImagesAdapter
import com.virtualstudios.imagefilters.databinding.ActivitySavedImagesBinding
import com.virtualstudios.imagefilters.listeners.SavedImageListener
import com.virtualstudios.imagefilters.utilities.displayToast
import com.virtualstudios.imagefilters.viewmodels.SavedImagesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class SavedImagesActivity : AppCompatActivity(), SavedImageListener {

    private lateinit var binding: ActivitySavedImagesBinding
    private val viewModel: SavedImagesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        setListeners()
        viewModel.loadSavedImages()
    }

    private fun setUpObserver(){
        viewModel.savedImagesUiState.observe(this, Observer {
            val savedImagesDataState = it ?: return@Observer
            binding.savedImagesProgressBar.visibility =
                if (savedImagesDataState.isLoading) View.VISIBLE else View.GONE
            savedImagesDataState.savedImages?.let { savedImages ->
                SavedImagesAdapter(savedImages, this).also { adapter ->
                    with(binding.savedImagesRecyclerView){
                        this.adapter = adapter
                        visibility = View.VISIBLE
                    }
                }
            } ?: run {
                savedImagesDataState.error?.let { error ->
                    displayToast(error)
                }
            }
        })
    }

    private fun setListeners(){
        binding.imageBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onImageClick(file: File) {
        val fileUri = FileProvider.getUriForFile(
            applicationContext,
            "${packageName}.provider",
            file
        )
        Intent(
            applicationContext,
            FilteredImageActivity::class.java
        ).also { filteredImageIntent ->
            filteredImageIntent.putExtra(EditImageActivity.KEY_FILTERED_IMAGE_URI, fileUri)
            startActivity(filteredImageIntent)
        }
    }
}