import com.google.gson.annotations.SerializedName

/**
 * Created by Cillian Myles on 04/05/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

data class User(
        @SerializedName("id") val id: String,
        @SerializedName("auth_key") val authKey: String,
        @SerializedName("first_name") val first: String,
        @SerializedName("last_name") val last: String,
        @SerializedName("company_name") val company: String) {
    init {
        require(id.isNotBlank()) { "User needs id to be valid" }
    }
}
