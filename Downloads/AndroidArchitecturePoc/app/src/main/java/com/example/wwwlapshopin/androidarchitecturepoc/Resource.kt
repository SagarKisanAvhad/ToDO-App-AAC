package com.example.wwwlapshopin.androidarchitecturepoc

data class Resource<out T> constructor(val status: Status, val data: T? = null, val message: String? = null) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                    Status.SUCCESS,
                    data,
                    null
            )
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(
                    Status.ERROR,
                    data,
                    msg
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                    Status.LOADING,
                    data,
                    null
            )
        }
    }
}