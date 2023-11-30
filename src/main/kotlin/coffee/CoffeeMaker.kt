package coffee

import common.data.Beverage
import common.data.BeverageOrder
import common.BeverageMaker

class CoffeeMaker(
    override val beverage: Beverage,
    espresso: Int,
    foam: Int,
    steamedMilk: Int,
    hotChocolate: Int
) : BeverageMaker {
    private val decorator = CoffeeDecorator(
        espresso = espresso,
        foam = foam,
        steamedMilk = steamedMilk,
        hotChocolate = hotChocolate
    )

    override fun prepareOrder(): BeverageOrder {
        return BeverageOrder(beverage.name, decorator.iterator(), calculatePrice())
    }

    private fun calculatePrice() = beverage.price +
            (decorator.espresso * .2) +
            (decorator.foam * 0.2) +
            (decorator.steamedMilk * 0.2) +
            (decorator.hotChocolate * .2)

}