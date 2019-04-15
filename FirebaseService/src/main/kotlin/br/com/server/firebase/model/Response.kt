package br.com.server.firebase.model

data class Response(
    var error: ApiError? = null,
    var data: List<AppUser>? = null
) {
    constructor(function: Response.() -> Unit) : this() {
        function.invoke(this)
    }
}


