package common.factory

import common.ResourceService
import common.data.BaseResult
import common.data.Beverage
import common.data.BeverageOrder

interface BeverageRepository {
    val resourceService : ResourceService
    fun prepareBeverage(beverage: Beverage) : BaseResult<BeverageOrder, String>

    fun getAllBeverage() : BaseResult<List<Beverage>, String>
}