package com.virtualstudios.imagefilters.activities.editimage

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.virtualstudios.imagefilters.activities.main.MainActivity
import com.virtualstudios.imagefilters.databinding.ActivityEditImageBinding
import com.virtualstudios.imagefilters.utilities.displayToast
import com.virtualstudios.imagefilters.utilities.show
import com.virtualstudios.imagefilters.viewmodels.EditImageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditImageBinding

    private val viewModel: EditImageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        setUpObservers()
        prepareImagePreview()
    }

    private fun setUpObservers(){
        viewModel.imagePreviewUiState.observe(this, {
            val dataState = it ?: return@observe
            binding.previewProgress.visibility =
                if (dataState.isLoading) View.VISIBLE else View.GONE
            dataState.bitmap?.let { bitmap ->
                binding.imagePreview.setImageBitmap(bitmap)
                binding.imagePreview.show()
            } ?: kotlin.run {
                dataState.error?.let { error ->
                    displayToast(error)
                }
            }
        })
    }

    private fun prepareImagePreview(){
        intent.getParcelableExtra<Uri>(MainActivity.KEY_IMAGE_URI)?.let { imageUri ->
            viewModel.prepareImagePreview(imageUri)
        }
    }


    private fun setListeners(){
        binding.imageBack.setOnClickListener { onBackPressed() }
    }
}