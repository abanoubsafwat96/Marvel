package com.abanoub.marvel.data.model

import android.content.ClipData.Item


class CharacterData {

    private var title: String? = null
    private var thumbnail: Thumbnail? = null

    fun getTitle(): String? {
        return title
    }

    fun getThumbnail(): Thumbnail? {
        return thumbnail
    }
}
