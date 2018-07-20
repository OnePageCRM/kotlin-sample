import com.google.gson.annotations.SerializedName

/**
 * Created by Cillian Myles on 04/05/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

data class ContactsResponse(
        @SerializedName("status") private val status: Int,
        @SerializedName("message") private val message: String,
        @SerializedName("timestamp") private val timestamp: Long,
        @SerializedName("data") private val data: ContactsData) {

    data class ContactsData(
            @SerializedName("contacts") private var contacts: List<ContactWrapper>)

    data class ContactWrapper(
            @SerializedName("contact") private var contact: Contact)
}
