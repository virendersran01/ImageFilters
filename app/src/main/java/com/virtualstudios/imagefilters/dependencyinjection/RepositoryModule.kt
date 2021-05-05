package com.virtualstudios.imagefilters.dependencyinjection

import com.virtualstudios.imagefilters.respositories.EditImageRepository
import com.virtualstudios.imagefilters.respositories.EditImageRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<EditImageRepository> { EditImageRepositoryImpl(androidContext()) }
}