package com.virtualstudios.imagefilters.dependencyinjection

import com.virtualstudios.imagefilters.viewmodels.EditImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EditImageViewModel(editImageRepository = get()) }
}