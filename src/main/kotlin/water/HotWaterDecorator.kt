package water

import common.data.Decorator

class HotWaterDecorator() : Decorator() {
    override fun iterator(): Iterator<Pair<String, String>> {
        return listOf(
            "size" to "$size",
            "temperature" to "$temperature"
        ).listIterator()
    }
}