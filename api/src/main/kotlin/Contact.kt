import com.google.gson.annotations.SerializedName

/**
 * Created by Cillian Myles on 04/05/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

data class Contact(
        @SerializedName("id") val id: String,
        @SerializedName("first_name") val first: String,
        @SerializedName("last_name") val last: String,
        @SerializedName("company_name") val company: String) {
    init {
        require(id.isNotBlank()) { "Contact needs id to be valid" }
        require(last.isNotBlank() || company.isNotBlank()) { "Contact needs last or company to be valid" }
    }
}
