package com.abanoub.marvel.data.model

import android.content.ClipData.Item


class Comics {

    private var available: Int? = null
    private var collectionURI: String? = null
    private var items: List<Item?>? = null
    private var returned: Int? = null

    fun getAvailable(): Int? {
        return available
    }

    fun setAvailable(available: Int?) {
        this.available = available
    }

    fun getCollectionURI(): String? {
        return collectionURI
    }

    fun setCollectionURI(collectionURI: String?) {
        this.collectionURI = collectionURI
    }

    fun getItems(): List<Item?>? {
        return items
    }

    fun setItems(items: List<Item?>?) {
        this.items = items
    }

    fun getReturned(): Int? {
        return returned
    }

    fun setReturned(returned: Int?) {
        this.returned = returned
    }
}
