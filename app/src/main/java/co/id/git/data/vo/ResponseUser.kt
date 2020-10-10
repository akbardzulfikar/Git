package co.id.git.data.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseUser(

    @field:SerializedName("items")
    val items: List<User>
) : Parcelable