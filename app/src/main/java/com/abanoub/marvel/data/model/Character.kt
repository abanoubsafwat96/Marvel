package com.abanoub.marvel.data.model

import retrofit2.http.Url


class Character {

    private var id: Int? = null
    private var name: String? = null
    private var description: String? = null
    private var modified: String? = null
    private var thumbnail: Thumbnail? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getModified(): String? {
        return modified
    }

    fun setModified(modified: String?) {
        this.modified = modified
    }

    fun getThumbnails(): Thumbnail? {
        return thumbnail
    }
}