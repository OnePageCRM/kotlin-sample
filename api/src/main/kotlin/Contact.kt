import com.google.gson.annotations.SerializedName

/**
 * Created by Cillian Myles on 04/05/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

data class Contact(
        @SerializedName("id") private var id: String,
        @SerializedName("first_name") private var first: String,
        @SerializedName("last_name") private var last: String,
        @SerializedName("company_name") private var company: String) {
    init {
        require(id.isNotBlank()) { "Contact needs id to be valid" }
        require(last.isNotBlank() || company.isNotBlank()) { "Contact needs last or company to be valid" }
    }
}
