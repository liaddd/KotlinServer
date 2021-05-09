package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val firstName: String? = null,
    val lastName: String? = null,
    val email : String? = null,
    val phone : String? = null,
    val birthDay : String? = null,
    val gender : Gender? = null,
    val password : String? = null,
    val id : Long? = null
)

@Serializable
enum class Gender{
    MALE,
    FEMALE,
    OTHER
}