package common.factory

import common.ResourceService
import common.data.BaseResult
import common.data.Beverage
import common.data.BeverageOrder

interface Factory {
    val beverage : Beverage
    val resourceService : ResourceService
    fun prepareBeverage() : BaseResult<BeverageOrder, String>
}