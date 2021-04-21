package com.example.models

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class User(
    val firstName: String? = null,
    val lastName: String? = null,
    val email : String? = null,
    val phone : String? = null,
    val birthDay : String? = null,
    val gender : Gender? = null,
    val password : String? = null,
    val id : Long = Random.nextLong(999999)
)

@Serializable
enum class Gender{
    MALE,
    FEMALE,
    OTHER
}