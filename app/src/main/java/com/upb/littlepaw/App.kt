package com.upb.littlepaw

import android.app.Application
import com.upb.littlepaw.changepassscreen.ChangePasswordViewModel
import com.upb.littlepaw.data.api.ApiClient
import com.upb.littlepaw.data.persistency.AuthPersistency
import com.upb.littlepaw.data.persistency.RoomPersistency
import com.upb.littlepaw.data.repositories.PetsRepository
import com.upb.littlepaw.data.repositories.UsersRepository
import com.upb.littlepaw.homescreen.HomeViewModel
import com.upb.littlepaw.homescreen.addpet.fragments.viewmodels.AddPetViewModel
import com.upb.littlepaw.homescreen.adoption.AdoptionViewModel
import com.upb.littlepaw.homescreen.profile.fragments.viewmodels.ProfileViewModel
import com.upb.littlepaw.loginviewmodel.LoginViewModel
import com.upb.littlepaw.register.viewmodels.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App:Application() {
    val appModule = module{
        single { ApiClient() }
        single { PetsRepository(get(), get(), get()) }
        single{RoomPersistency(get())}
        single { AuthPersistency(get()) }
        single { UsersRepository(get(),get() ) }
        viewModel { AdoptionViewModel(get(), get()) }
        viewModel { AddPetViewModel(get(), get()) }
        viewModel {ChangePasswordViewModel(get())}
        viewModel { RegisterViewModel(get()) }
        viewModel {HomeViewModel(get())}
        viewModel{ProfileViewModel(get())}
        viewModel{LoginViewModel(get())}


    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }

}