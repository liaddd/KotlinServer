package com.example.routing

import com.example.extensions.replace
import com.example.models.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

private var users = mutableListOf<User>()

fun Routing.user(){

    route("/user") {

        // create user
        post{
            val user = call.receive<User>()
            if(users.any { it.email == user.email }) return@post call.respond(HttpStatusCode.BadRequest, "User with email: ${user.email} already exist!")
            users.add(user)
            println("user ${user.firstName} created successfully!")
            call.respond(user)
        }

        // get user by id
        get("{id}") {
            val id = call.parameters["id"]?.toLong() ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            val user = users.find { it.id == id } ?: return@get call.respond(HttpStatusCode.NotFound , "could\'nt find user with id: $id")
            call.respond(user)
        }

        // get all users
        get{
            call.respond(users)
        }

        put {
            val user = call.receive<User>()
            if(users.any { it.email == user.email }) return@put call.respond(HttpStatusCode.BadRequest, "User with email: ${user.email} already exist!")
            val oldUser = users.find { it.email == user.email }
            users.replace(oldUser, user)
            println("user ${user.firstName} updated successfully!")
            call.respond(user)
        }
    }
}