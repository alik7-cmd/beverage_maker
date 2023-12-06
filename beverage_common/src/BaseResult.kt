sealed class BaseResult<out T : Any, out V : Any> {
    data class Success<T : Any,>(val content: T) : BaseResult<T, Nothing>()
    data class Error<V : Any>(val msg : V): BaseResult<Nothing, V>()
}