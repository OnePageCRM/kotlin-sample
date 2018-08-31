import com.google.gson.annotations.SerializedName

/**
 * Created by Cillian Myles on 28/04/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

data class LoginResponse(
        @SerializedName("status") val status: Int,
        @SerializedName("message") val message: String,
        @SerializedName("timestamp") val timestamp: Long,
        @SerializedName("data") val data: LoginData) {

    data class LoginData(
            @SerializedName("user_id") val userId: String,
            @SerializedName("auth_key") val authKey: String,
            @SerializedName("user") val loggedUser: LoggedUser)

    data class LoggedUser(
            @SerializedName("user") val user: User)
}
