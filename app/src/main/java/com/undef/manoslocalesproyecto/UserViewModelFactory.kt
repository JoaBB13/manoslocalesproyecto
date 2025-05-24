package com.undef.manoslocalesproyecto

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = UserRepository(context)
        val preferences = UserPreferencesLogin(context)
        return UserViewModel(repository, preferences) as T
    }
}
