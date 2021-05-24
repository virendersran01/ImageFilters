package com.virtualstudios.imagefilters.listeners

import java.io.File

interface SavedImageListener {
    fun onImageClick(file: File)
}