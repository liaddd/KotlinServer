package com.example.routing

import com.example.models.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.routing.Routing

fun Routing.root() {
    get("/") {
        call.respondText("Nothing here O_o", ContentType.Text.Plain)
    }
}