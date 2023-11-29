package observer

class Subject<T>(var state: T) {
    private val observers: MutableList<Observer<T>> = mutableListOf()

    fun attach(observer: Observer<T>) {
        observers.add(observer)
    }

    private fun notifyAllObservers() {
        for (observer in observers) {
            observer.update()
        }
    }
}
