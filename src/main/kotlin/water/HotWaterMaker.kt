package water

import common.data.Beverage
import common.data.BeverageOrder
import common.BeverageMaker

class HotWaterMaker(override val beverage: Beverage) : BeverageMaker {
    private val decorator = HotWaterDecorator()
    override fun prepareOrder(): BeverageOrder {
        return BeverageOrder(beverage.name, decorator.iterator(), beverage.price)
    }

}