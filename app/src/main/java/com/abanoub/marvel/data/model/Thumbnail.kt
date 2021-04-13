package com.abanoub.marvel.data.model

class Thumbnail {

    private var path: String? = null
    private var extension: String? = null

    fun getPath(): String? {
        return path
    }

    fun setPath(path: String?) {
        this.path = path
    }

    fun getExtension(): String? {
        return extension
    }

    fun setExtension(extension: String?) {
        this.extension = extension
    }
}
