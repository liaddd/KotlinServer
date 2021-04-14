package com.example.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class User(
    val firstName: String? = null,
    val lastName: String? = null,
    val email : String? = null,
    val phone : String? = null,
    val birthDate : String? = null,
    val gender : Gender? = null
){
    val id : Long = Random.nextLong(0, 999999)
}

@Serializable
enum class Gender{
    @SerialName("Male") MALE,
    @SerialName("Female") FEMALE,
    @SerialName("Other") OTHER
}