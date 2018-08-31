import com.google.gson.annotations.SerializedName

/**
 * Created by Cillian Myles on 31/08/2018.
 * Copyright (c) 2018 Cillian Myles. All rights reserved.
 */

data class ContactForm(
        @SerializedName("first_name") val first: String,
        @SerializedName("last_name") val last: String,
        @SerializedName("company_name") val company: String) {
    init {
        require(last.isNotBlank() || company.isNotBlank()) { "Contact needs last or company to be valid" }
    }
}