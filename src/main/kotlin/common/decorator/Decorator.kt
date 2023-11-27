package common.decorator

import common.data.BeverageSize

abstract class Decorator(var size: BeverageSize = BeverageSize.MEDIUM,
                         var temperature : Float = 50.0f){

    abstract operator fun iterator() : Iterator<Pair<String, String>>
}