package br.com.server.firebase.model

data class ApiError(
    var message: String? = null,
    var code: Int = -1
) {
    constructor(function: ApiError.() -> Unit) : this() {
        function.invoke(this)
    }
}