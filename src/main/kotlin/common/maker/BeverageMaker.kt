package common.maker

import common.data.Beverage
import common.data.BeverageOrder
import common.decorator.Decorator

interface BeverageMaker {
    val beverage : Beverage
    fun prepareOrder() : BeverageOrder
}