package com.undef.manoslocalesproyecto


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manoslocalesproyecto.User
import com.undef.manoslocalesproyecto.UserRepository
import com.undef.manoslocalesproyecto.UserPreferencesLogin
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow


class UserViewModel(
    private val repository: UserRepository,
    private val preferences: UserPreferencesLogin
) : ViewModel() {

    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState: StateFlow<Boolean?> = _loginState

    fun register(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
            preferences.saveUserEmail(user.email)
            _loginState.value = true
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = repository.getUserByEmail(email)  // Esto devuelve User? directamente
            _loginState.value = user?.password == password
            if (_loginState.value == true) {
                preferences.saveUserEmail(email)
            }
        }
    }

    fun changePassword(email: String, newPassword: String) {
        viewModelScope.launch {
            val user = repository.getUserByEmail(email)
            if (user != null) {
                val updatedUser = user.copy(password = newPassword)
                repository.updateUser(updatedUser)
            }
        }
    }

    fun checkUserExists(email: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = repository.getUserByEmail(email)
            callback(user != null)
        }
    }



    fun logout() {
        preferences.clear()
        _loginState.value = false
    }

    fun checkLoginStatus() {
        val email = preferences.getUserEmail()
        _loginState.value = email != null
    }
}
