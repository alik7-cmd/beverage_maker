package common

import common.data.Beverage
import common.data.BeverageOrder

interface BeverageMaker {
    val beverage : Beverage
    fun prepareOrder() : BeverageOrder
}