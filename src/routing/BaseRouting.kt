package com.example.routing

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.body
import kotlinx.html.colorInput
import kotlinx.html.h1
import kotlinx.html.style
import java.awt.Color

fun Routing.root() {
    get("/") {
        call.respondHtml {
            body {
                h1 {
                    text("Nothing here O_o")
                }
            }
        }
    }
}