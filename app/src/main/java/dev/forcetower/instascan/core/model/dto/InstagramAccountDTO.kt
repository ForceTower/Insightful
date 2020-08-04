package dev.forcetower.instascan.core.model.dto

import android.os.Parcel
import android.os.Parcelable

data class InstagramAccountDTO(
    val id: String,
    val igId: Long,
    val name: String,
    val username: String,
    val biography: String?,
    val profilePictureUrl: String?,
    val followersCount: Int,
    val followsCount: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeLong(igId)
        parcel.writeString(name)
        parcel.writeString(username)
        parcel.writeString(biography)
        parcel.writeString(profilePictureUrl)
        parcel.writeInt(followersCount)
        parcel.writeInt(followsCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InstagramAccountDTO> {
        override fun createFromParcel(parcel: Parcel): InstagramAccountDTO {
            return InstagramAccountDTO(parcel)
        }

        override fun newArray(size: Int): Array<InstagramAccountDTO?> {
            return arrayOfNulls(size)
        }
    }
}