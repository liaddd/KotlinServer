package com.example.routing

import com.example.database.Tables
import com.example.database.UserDao
import com.example.extensions.replace
import com.example.models.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.user(dao : UserDao){

    route("/user") {

        // get all users
        get{
            val dbUsers = dao.getAllUsers()
            println("users: $dbUsers")
            call.respond(dbUsers)
        }

        // create user
        post{
            val users = dao.getAllUsers()
            val user = call.receive<User>()
            println("user posted is: $user")
            if(users.any { it.email == user.email }) return@post call.respond(HttpStatusCode.BadRequest, "User with email: ${user.email} already exist!")
            //users.add(user)
            //dao.createUser(user)
            println("user ${user.firstName} created successfully!")
            dao.createUser(user)
            call.respond(user)
        }

        // get user by id
        get("{id}") {
            val id = call.parameters["id"]?.toLong() ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            val user = dao.getUserById(id) ?: return@get call.respond(HttpStatusCode.NotFound , "could\'nt find user with id: $id")
            call.respond(user)
        }

        // update user
        put {
            val users = dao.getAllUsers()

            val user = call.receive<User>()
            if(users.any { it.email == user.email }) return@put call.respond(HttpStatusCode.BadRequest, "User with email: ${user.email} already exist!")
            val oldUser = users.find { it.email == user.email }
            users.replace(oldUser, user)
            println("user ${user.firstName} updated successfully!")
            call.respond(user)
        }

        // login with email & password
        post("login"){
            val userEmail = call.parameters["email"]
            val userPassword = call.parameters["password"]
            if (userEmail.isNullOrBlank() || userPassword.isNullOrBlank()) return@post call.respond(HttpStatusCode.BadRequest, "missing email OR password")

            val user = dao.getUserByEmailAndPassword(userEmail, userPassword)
            if (user != null) call.respond(user)
            else call.respond(HttpStatusCode.NotFound, "User not found")
        }

        // delete user by id
        delete("{id}") {
            val userId = call.parameters["id"]
            userId?.toLong()?.let {
                dao.deleteUser(it)
                call.respond("User with id: $userId deleted successfully!")
            } ?: call.respond(HttpStatusCode.NotFound, "User with id: $userId not found")
        }
    }
}