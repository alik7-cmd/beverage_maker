package chocolate

import common.data.BeverageSize
import common.data.Decorator

class HotChocolateDecorator : Decorator(BeverageSize.MEDIUM, 10.0f) {
    override fun toString() = "Size -> $size\n" +
            "Temperature -> $temperature"
}