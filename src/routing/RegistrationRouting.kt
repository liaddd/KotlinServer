package com.example.routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.login() {
    get("/login") {
        val x = call.request.queryParameters["id"]
        println("its $x")
        call.respondText("Hi $x", ContentType.Text.Plain)
    }
}