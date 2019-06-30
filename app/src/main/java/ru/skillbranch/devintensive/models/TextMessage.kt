package ru.skillbranch.devintensive.models

import java.util.*

class TextMessage(
        id: String,
        from: User?,
        chat: Chat,
        isIncomming: Boolean = false,
        date: Date = Date(),
        var text:String?
) : BaseMessage(id, from, chat, isIncomming, date) {
    override fun formatMessage(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}