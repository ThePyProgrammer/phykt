package com.thepyprogrammer.ktlib

infix fun <T, R> T.`=`(other: R) = this to other

class Object<T, R>(vararg vals: Pair<T, R>): HashMap<T, R>(hashMapOf(*vals)) {
    /**
     * duplicates the let keyword in JavaScript
     * declare multiple keywords in the object
     */
    fun let(vararg pairs: Pair<T, R>) {
        putAll(hashMapOf(*pairs))
    }
}

/**
 * struct like in Swift, intertextuality it is.
 */
fun <T, R> struct(vararg vals: Pair<T, R>, apply: Object<T, R>.() -> Unit = {}) = Object(*vals).apply(apply)

fun test() {
    var other: Int = 5
    val obj = struct(
        "five" `=` 10,
        "hello" `=` 99,
        9 `=` 99,
        20.4 `=` 0,
        other `=` null
    ) {
        other = this["five"] ?: 5
    }

    obj.let(
        "this" `=` 5,
        "hello" `=` 20,
        "99" `=` 99
    )
}