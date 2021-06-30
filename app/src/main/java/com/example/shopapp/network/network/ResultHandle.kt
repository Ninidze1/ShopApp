package com.example.shopapp.network.network

data class ResultHandle <T> (val status: Status, val data: T? = null, val error: String? = null) {

    companion object {
        fun <T> success(data: T?): ResultHandle<T> {
            return ResultHandle(Status.SUCCESS, data, null)
        }

        fun <T> error(error: String?): ResultHandle<T> {
            return ResultHandle(Status.ERROR, null, error)
        }

        enum class Status {
            SUCCESS,
            ERROR
        }
    }

}