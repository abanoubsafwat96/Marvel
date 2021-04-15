package com.abanoub.marvel.data.model

import android.os.Parcel
import android.os.Parcelable
import retrofit2.http.Url


class Character() :Parcelable {

    private var id: Int? = null
    private var name: String? = null
    private var description: String? = null
    private var modified: String? = null
    private var thumbnail: Thumbnail? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        description = parcel.readString()
        modified = parcel.readString()
        thumbnail = parcel.readParcelable(Thumbnail::class.java.classLoader)
    }

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(modified)
        parcel.writeParcelable(thumbnail, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}