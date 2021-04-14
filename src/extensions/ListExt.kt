package com.example.extensions

fun <E> Iterable<E>.replace(old: E, new: E) = map { if (it == old) new else it }
