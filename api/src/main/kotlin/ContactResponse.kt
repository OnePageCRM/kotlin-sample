import com.google.gson.annotations.SerializedName

/**
 * Created by Cillian Myles on 31/08/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

data class ContactResponse(
        @SerializedName("status") val status: Int,
        @SerializedName("message") val message: String,
        @SerializedName("timestamp") val timestamp: Long,
        @SerializedName("data") val data: ContactData) {

    data class ContactData(
            @SerializedName("contact") val contact: Contact)
}
