import com.google.gson.annotations.SerializedName

/**
 * Created by Cillian Myles on 04/05/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

data class User(
        @SerializedName("id") private var id: String,
        @SerializedName("auth_key") private var authKey: String,
        @SerializedName("first_name") private var first: String,
        @SerializedName("last_name") private var last: String,
        @SerializedName("company_name") private var company: String) {
    init {
        require(id.isNotBlank(), { "User needs id to be valid" })
    }
}
