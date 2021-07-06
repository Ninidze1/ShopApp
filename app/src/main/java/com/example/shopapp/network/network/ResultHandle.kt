package com.example.shopapp.network.network


data class ResultHandle<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null,
    val loading: Boolean = false
) {

    companion object {
        fun <T> success(data: T): ResultHandle<T> {
            return ResultHandle(Status.SUCCESS, data)
        }

        fun <T> error(message: String): ResultHandle<T> {
            return ResultHandle(Status.ERROR, null, message)
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
    }


}