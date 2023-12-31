package common

import BaseResult
import chocolate.HotChocolate
import chocolate.HotChocolateMaker
import coffee.CoffeeMaker
import coffee.cappuccino.Cappuccino
import coffee.latte.Latte
import coffee.mocha.Mocha
import common.data.Beverage
import common.data.BeverageOrder
import common.data.BeverageType
import tea.HerbalTea
import water.HotWater
import water.HotWaterMaker

class BeverageRepositoryImpl(
    override val resourceService: ResourceService

) : BeverageRepository {
    override fun prepareBeverage(
        beverage: Beverage, espresso: Int,
        foam: Int,
        steamedMilk: Int,
        hotChocolate: Int
    ): BaseResult<BeverageOrder, String> {
        return if (resourceService.isResourceAvailable()) {
            val maker = when (beverage.type) {
                BeverageType.COFFEE -> CoffeeMaker(beverage, espresso, foam, steamedMilk, hotChocolate)
                BeverageType.WATER, BeverageType.TEA -> HotWaterMaker(beverage)
                BeverageType.CHOCOLATE -> HotChocolateMaker(beverage)
            }
            BaseResult.Success(maker.prepareOrder())
        } else {
            BaseResult.Error("Something went wrong! Please try again")
        }
    }

    override fun getAllBeverage(): BaseResult<List<Beverage>, String> {
        val listOfBeverage = mutableListOf<Beverage>().apply {
            add(Mocha())
            add(Cappuccino())
            add(HotWater())
            add(HotChocolate())
            add(HerbalTea())
            add(Latte())
        }
        return BaseResult.Success(listOfBeverage)
    }
}