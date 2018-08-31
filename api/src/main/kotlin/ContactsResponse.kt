import com.google.gson.annotations.SerializedName

/**
 * Created by Cillian Myles on 04/05/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

data class ContactsResponse(
        @SerializedName("status") val status: Int,
        @SerializedName("message") val message: String,
        @SerializedName("timestamp") val timestamp: Long,
        @SerializedName("data") val data: ContactsData) {

    data class ContactsData(
            @SerializedName("contacts") val contacts: List<ContactWrapper>)

    data class ContactWrapper(
            @SerializedName("contact") val contact: Contact)
}
