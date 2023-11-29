package observer

class MachineStateObserver<T>(override val subject: Subject<T>) : Observer <T>{
    override fun update() {

    }
}