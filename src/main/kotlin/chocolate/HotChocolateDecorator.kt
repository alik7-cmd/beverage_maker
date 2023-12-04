package chocolate

import common.data.BeverageSize
import common.data.Decorator

class HotChocolateDecorator : Decorator(BeverageSize.MEDIUM, 10.0f) {
    override fun iterator(): Iterator<Pair<String, String>> {
        return listOf(
            "Size" to "$size",
            "Temperature" to "$temperature"
        ).listIterator()
    }
}