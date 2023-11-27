package presentation

import chocolate.HotChocolate
import coffee.mocha.Mocha
import coffee.cappuccino.Cappuccino
import common.ResourceServiceImpl
import common.data.BaseResult
import common.data.Beverage
import common.factory.BeverageFactory
import water.HotWater

fun main() {

    val listOfBeverage = mutableListOf<Beverage>()
    listOfBeverage.add(Mocha())
    listOfBeverage.add(Cappuccino())
    listOfBeverage.add(HotWater())
    listOfBeverage.add(HotChocolate())

    listOfBeverage.forEach {
        println("The name of the beverage is ${it.name} and price is ${it.price}")
    }
    println()

    val factory  = BeverageFactory(listOfBeverage[1],  ResourceServiceImpl())
    when(val response = factory.prepareBeverage()){
        is BaseResult.Success -> {
            println("Please collect your ${response.order.name}")
            while (response.order.decorator.hasNext()){
                val decoratorItem = response.order.decorator.next()
                println("${decoratorItem.first} --> ${decoratorItem.second}" )
            }
        }

        is BaseResult.Error ->{
            println(response.msg)
        }
    }

}