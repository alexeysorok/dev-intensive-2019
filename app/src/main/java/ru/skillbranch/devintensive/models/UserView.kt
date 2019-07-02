package ru.skillbranch.devintensive.models

// как можно мапить с помощью DTO класса User
// значения на класс UserView

class UserView(
    val id: String,
    val fullName: String,
    val nickName: String,
    val avatar: String? = null,
    var status: String = "offline",
    val initials: String?
) {

    fun printMe() {
        println("""
            id: $id
            fullName:$fullName
            nickName:$nickName
            avatar: $avatar
            status: $status
            initials:$initials           
            
        """.trimIndent())
    }
}