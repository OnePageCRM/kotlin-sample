import com.google.gson.annotations.SerializedName

/**
 * Created by Cillian Myles on 28/04/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

data class LoginResponse(
        @SerializedName("status") private val status: Int,
        @SerializedName("message") private val message: String,
        @SerializedName("timestamp") private val timestamp: Long,
        @SerializedName("data") private val data: LoginData) {

    data class LoginData(
            @SerializedName("id") private var id: String,
            @SerializedName("auth_key") private var authKey: String,
            @SerializedName("user") private var loggedUser: LoggedUser)

    data class LoggedUser(
            @SerializedName("user") private var user: User)
}
