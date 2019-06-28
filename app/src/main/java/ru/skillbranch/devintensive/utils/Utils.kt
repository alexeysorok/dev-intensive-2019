package ru.skillbranch.devintensive.utils

// статическое объявление функций внутри объектов, типа singleTon
object Utils {
    fun parseFullName(fullName: String?) : Pair<String?, String?>{
        val parts = fullName?.split(" ")
        var firstName = parts?.getOrNull(0).orEmpty()
        var lastName = parts?.getOrNull(1)



        // Pair возвращает два значения
        //return Pair(firstName, lastName)

        if (firstName == null || firstName == "") firstName = "null"
        if (lastName == null || lastName == "") lastName = "null"

        // более короткая запись
        return (firstName to lastName)
    }
}