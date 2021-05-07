package com.virtualstudios.imagefilters.listeners

import com.virtualstudios.imagefilters.data.ImageFilter

interface ImageFilterListener {
    fun onFilterSelected(imageFilter: ImageFilter)
}