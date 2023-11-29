package observer

interface Observer<T> {
    val subject : Subject<T>
    fun update()
}