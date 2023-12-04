package coffee

import common.data.Decorator

class CoffeeDecorator(
    val espresso: Int,
    val foam: Int,
    val steamedMilk: Int,
    val hotChocolate: Int
) : Decorator() {
    override operator fun iterator(): Iterator<Pair<String, String>> {
        return listOf(
            "Size" to "$size",
            "Temperature" to "$temperature",
            "Espresso" to "$espresso",
            "Foam" to "$foam",
            "Milk" to "$steamedMilk",
            "Chocolate" to "$hotChocolate"
        ).listIterator()
    }
}