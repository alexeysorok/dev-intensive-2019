package ru.skillbranch.devintensive.utils

// статическое объявление функций внутри объектов, типа singleTon
object Utils {
    fun parseFullName(fullName: String?) : Pair<String?, String?>{
        val parts = fullName?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)



        // Pair возвращает два значения
        //return Pair(firstName, lastName)

        if (firstName == null || firstName == "") firstName = null
        if (lastName == null || lastName == "") lastName = null

        // более короткая запись
        return (firstName to lastName)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        val initialFirstName: String? = when {
            firstName.isNullOrEmpty() -> null
            else -> firstName.trim()[0].toUpperCase().toString()
        }

        val initialLastName: String? = when {
            lastName.isNullOrEmpty() -> null
            else -> lastName.trim()[0].toUpperCase().toString()
        }

        val result: String? = when {
            initialFirstName.isNullOrEmpty() -> initialLastName
            initialLastName.isNullOrEmpty() -> initialFirstName
            initialFirstName.isNullOrEmpty() && initialLastName.isNullOrEmpty() -> null
            initialFirstName.isNullOrBlank() && initialLastName.isNullOrBlank() -> null

            else -> initialFirstName + initialLastName
        }


        return result
    }

    fun transliteration(payload: String, divider: String = " "): String {

        // словарь
        val mapCirilic: Map<String, String>  = mapOf("а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya")

        // вспомогательный массив
        var listResultWord: MutableList<String> = mutableListOf()

        var listWords = payload.toList()
        for (word in listWords) {

            var isUpper = word.isUpperCase()
            var item = word.toLowerCase().toString()

            if (mapCirilic.containsKey(item)) {

                // добавляем в список если буква есть в словаре
                if (isUpper) {listResultWord.add(mapCirilic.get(item).toString().toUpperCase())}

                else {listResultWord.add(mapCirilic.get(item).toString())}


            }
            // если нет в списке доабавляем без измененний
            else listResultWord.add(word.toString())
        }

        val resultWord= listResultWord.joinToString("")
        val listResult = resultWord.split( " ")
        val result = listResult.joinToString(divider)


        return result

    }
}