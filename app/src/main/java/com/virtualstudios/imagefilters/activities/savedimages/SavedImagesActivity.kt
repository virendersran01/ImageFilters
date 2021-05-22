package com.virtualstudios.imagefilters.activities.savedimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.virtualstudios.imagefilters.databinding.ActivitySavedImagesBinding

class SavedImagesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySavedImagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}