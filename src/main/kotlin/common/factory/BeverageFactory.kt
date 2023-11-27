package common.factory

import chocolate.HotChocolateMaker
import coffee.CoffeeMaker
import common.ResourceService
import common.data.BaseResult
import common.data.Beverage
import common.data.BeverageOrder
import common.data.BeverageType
import common.decorator.Decorator
import water.HotWaterMaker

class BeverageFactory(
    override val beverage: Beverage,
    override val resourceService: ResourceService

) : Factory {
    override fun prepareBeverage() : BaseResult<BeverageOrder, String> {
        return if(resourceService.isResourceAvailable()){
            val maker = when(beverage.item){
                BeverageType.COFFEE ->  CoffeeMaker(beverage)
                BeverageType.WATER -> HotWaterMaker(beverage)
                BeverageType.CHOCOLATE -> HotChocolateMaker(beverage)
            }
            BaseResult.Success(maker.prepareOrder())
        }else{
            BaseResult.Error("Something went wrong! Please try again")
        }
    }
}