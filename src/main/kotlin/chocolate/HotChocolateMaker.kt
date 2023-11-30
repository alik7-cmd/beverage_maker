package chocolate

import common.data.Beverage
import common.data.BeverageOrder
import common.BeverageMaker

class HotChocolateMaker(override val beverage: Beverage) : BeverageMaker {
    private val decorator = HotChocolateDecorator()

    override fun prepareOrder(): BeverageOrder {
        return BeverageOrder(beverage.name, decorator.iterator(), beverage.price)
    }
}