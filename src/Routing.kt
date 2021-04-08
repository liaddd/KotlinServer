package com.example

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.routing.Routing


fun Routing.root() {
    get("/") {
        call.respondText("Itzik its working!", ContentType.Text.Plain)
    }
}

fun Routing.userName(){
    get("/user"){
        val x = call.request.queryParameters["name"]?.first()?.toUpperCase()
        println("its $x")
        call.respondText("Hi $x", ContentType.Text.Plain)
    }
}