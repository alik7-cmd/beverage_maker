package common.data


open class Beverage(
    val type: BeverageType,
    val name : String,
    val price: Double,
    val coffeeType : CoffeeType = CoffeeType.NONE,
    val currency : Currency = Currency.EURO
)