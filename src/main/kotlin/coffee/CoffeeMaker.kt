package coffee

import common.data.Beverage
import common.data.BeverageOrder
import common.maker.BeverageMaker

class CoffeeMaker(override val beverage: Beverage) : BeverageMaker {
    private val decorator = CoffeeDecorator(
        espresso = 1,
        foam = 1,
        steamedMilk = 1,
        hotChocolate = 1)

    override fun prepareOrder(): BeverageOrder {
        return BeverageOrder(beverage.name, decorator.iterator(), calculatePrice())
    }

    private fun calculatePrice() = beverage.price +
            (decorator.espresso * .2) +
            (decorator.foam * 0.2) +
            (decorator.steamedMilk * 0.2) +
            (decorator.hotChocolate * .2)

}