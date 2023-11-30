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
            "espresso" to "$espresso",
            "foam" to "$foam",
            "steamedMilk" to "$steamedMilk",
            "hotChocolate" to "$hotChocolate"
        ).listIterator()
    }
}