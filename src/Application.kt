package com.example

import com.example.models.Gender
import com.example.routing.login
import com.example.routing.root
import com.example.routing.user
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json
import java.lang.Exception
import java.text.ParseException

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation){
        json(Json{
            prettyPrint = true
            isLenient = true
        })
    }

    routing {
        root()
        login()
        user()
    }
}

