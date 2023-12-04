package water

import common.data.Decorator

class HotWaterDecorator() : Decorator() {
    override fun iterator(): Iterator<Pair<String, String>> {
        return listOf(
            "Size" to "$size",
            "Temperature" to "$temperature"
        ).listIterator()
    }
}