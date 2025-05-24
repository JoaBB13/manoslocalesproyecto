package com.undef.manoslocalesproyecto

import android.content.Context

class UserRepository(context: Context) {
    private val userDao = AppDatabase.getDatabase(context).userDao()

    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun login(email: String, password: String) = userDao.getUser(email, password)
    suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)
    suspend fun updateUser(user: User) = userDao.updateUser(user)
}

