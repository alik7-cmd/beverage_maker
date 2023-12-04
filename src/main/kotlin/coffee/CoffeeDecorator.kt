package coffee

import common.data.Decorator

data class CoffeeDecorator(
    val espresso: Int,
    val foam: Int,
    val steamedMilk: Int,
    val hotChocolate: Int
) : Decorator() {
    override fun toString(): String {
        return  "Size -> $size\n" +
                "Temperature -> $temperature\n" +
                "Espresso -> $espresso\n" +
                "Foam -> $foam\n" +
                "Steamed Milk -> $steamedMilk\n" +
                "Hot Chocolate -> $hotChocolate\n"

    }
}