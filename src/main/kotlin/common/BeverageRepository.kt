package common

import common.data.BaseResult
import common.data.Beverage
import common.data.BeverageOrder

interface BeverageRepository {
    val resourceService: ResourceService
    fun prepareBeverage(
        beverage: Beverage,
        espresso: Int = 0,
        foam: Int = 0,
        steamedMilk: Int = 0,
        hotChocolate: Int = 0
    ): BaseResult<BeverageOrder, String>

    fun getAllBeverage(): BaseResult<List<Beverage>, String>
}