package com.virtualstudios.imagefilters.activities.savedimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.virtualstudios.imagefilters.databinding.ActivitySavedImagesBinding
import com.virtualstudios.imagefilters.utilities.displayToast
import com.virtualstudios.imagefilters.viewmodels.SavedImagesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedImagesActivity : AppCompatActivity() {

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
            displayToast("${savedImages.size}  images loaded")
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
}