import com.google.gson.annotations.SerializedName

/**
 * Created by Cillian Myles on 04/05/2018.
 * Copyright (c) 2018 OnePageCRM. All rights reserved.
 */

data class LoginForm(
        @SerializedName("login") var username: String,
        @SerializedName("password") var password: String) {
    init {
        require(username.contains("@")) { "Username must be valid email address" }
        require(password.length > 5) { "Password must be more than 5 chars" }
    }
}
