package com.virtualstudios.imagefilters.dependencyinjection

import com.virtualstudios.imagefilters.respositories.EditImageRepository
import com.virtualstudios.imagefilters.respositories.EditImageRepositoryImpl
import com.virtualstudios.imagefilters.respositories.SavedImagesRepository
import com.virtualstudios.imagefilters.respositories.SavedImagesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<EditImageRepository> { EditImageRepositoryImpl(androidContext()) }
    factory<SavedImagesRepository> { SavedImagesRepositoryImpl(androidContext()) }
}