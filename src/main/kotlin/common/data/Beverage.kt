package common.data


open class Beverage(
    val item: BeverageType,
    val name : String,
    val price: Double,
    val currency : Currency = Currency.EURO
)