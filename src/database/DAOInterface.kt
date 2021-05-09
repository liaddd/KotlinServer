package com.example.database

import com.example.models.User
import java.io.Closeable

interface DAOInterface : Closeable {

    fun init()
    fun createUser(user: User)
    fun updateUser(user: User)
    fun deleteUser(id: Long)
    fun getUserById(id: Long): User?
    fun getAllUsers(): List<User>
    fun getUserByEmailAndPassword(email: String, password: String) : User?
}