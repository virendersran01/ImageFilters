package com.virtualstudios.imagefilters.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.virtualstudios.imagefilters.respositories.SavedImagesRepository
import com.virtualstudios.imagefilters.utilities.Coroutines
import java.io.File

class SavedImagesViewModel(private val savedImagesRepository: SavedImagesRepository): ViewModel(){

    private val savedImageDataState = MutableLiveData<SavedImagesDataState>()
    val savedImagesUiState: LiveData<SavedImagesDataState> get() = savedImageDataState

    fun loadSavedImages(){
        Coroutines.io {
            kotlin.runCatching {
                emitSavedImagesUiState(isLoading = true)
                savedImagesRepository.loadSavedImages()
            }.onSuccess { savedImages ->
                if (savedImages.isNullOrEmpty()){
                    emitSavedImagesUiState(error = "No image found")
                }else{
                    emitSavedImagesUiState(savedImages = savedImages)
                }
            }.onFailure {
                emitSavedImagesUiState(error = it.message.toString())
            }
        }
    }

    private fun emitSavedImagesUiState(
        isLoading: Boolean = false,
        savedImages: List<Pair<File, Bitmap>>? = null,
        error: String? = null
    ){
        val dataState = SavedImagesDataState(isLoading, savedImages, error)
        savedImageDataState.postValue(dataState)
    }

    data class SavedImagesDataState(
        val isLoading: Boolean,
        val savedImages: List<Pair<File, Bitmap>>? = null,
        val error: String?
    )
}