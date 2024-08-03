package by.koajla.common

sealed interface ApiCallResult<T> {
    class Ok<T>(val data: T): ApiCallResult<T>
    class Err<T>(val error: Throwable): ApiCallResult<T>

    fun onSuccess(block: (T) -> Unit): ApiCallResult<T> {
        if (this is Ok) block(data); return this
    }
    fun onFailure(block: (Throwable) -> Unit): ApiCallResult<T> {
        if (this is Err) block(error); return this
    }
}