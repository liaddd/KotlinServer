package com.example.database

import com.example.models.Gender
import org.jetbrains.exposed.sql.Table

class Tables {
    object Users : Table() {
        val id = long("id").primaryKey().autoIncrement()
        val firstName = varchar("firstName", 100)
        val lastName = varchar("lastName", 100)
        val email = varchar("email", 100)
        val phone = varchar("phone", 13)
        val birthDay = varchar("birthDay", 100)
        val gender = enumeration("gender", Gender::class)
        val password = varchar("password", 10)
    }
}