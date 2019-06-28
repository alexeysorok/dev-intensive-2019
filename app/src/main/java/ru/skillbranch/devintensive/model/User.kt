package ru.skillbranch.devintensive.model

import java.util.*

data class User (
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false

)
{
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null)

//    constructor(id: String) : this(id, "Jack", "Grog")

    // статические функции класса
    // реализуем паттерн фабрика - позволяет создать объект экземпляра,
    // если перед этим потребуется сделать какие-то манипуляции, например преобразовать что-то, например
    // будут создаваться инкрементные идентификаторы или сделать какие-то расчеты
    companion object Factory{
        private var lastId: Int = -1
        fun makeUser(fullName: String?) : User{
            lastId++

            val parts = fullName?.split(" ")
            var firstName = parts?.getOrNull(0)
            var lastName = parts?.getOrNull(1)

            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}

