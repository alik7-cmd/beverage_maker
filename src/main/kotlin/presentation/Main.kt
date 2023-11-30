package presentation

import common.data.BaseResult
import common.data.Beverage
import common.ServiceLocator
import common.data.BeverageType
import java.util.*

fun main() {
    val input = Scanner(System.`in`)
    val repository = ServiceLocator.getBeverageRepository()
    var list : List<Beverage> = emptyList()
    when(val response = repository.getAllBeverage()){
        is BaseResult.Success ->{
            list = response.order
            list.forEachIndexed { index, beverage ->
                println(" ${index+1} The name of the beverage is ${beverage.name} and price is ${beverage.price}")

            }
        }
        is BaseResult.Error ->{
            println(response.msg)
        }
    }

    val index = input.nextInt()
    if(index <= list.size){
        println()
        val beverage = list[index-1]
        val orderState = when(beverage.type){
            BeverageType.COFFEE ->{
                println("Please add your options on your ${beverage.name}")
                println("Select Espresso shot (1-5):\n" +
                        "Select Foam (1-5):\n" +
                        "Select Steamed Milk shot (1-5):\n" +
                        "Select Hot chocolate (1-5):\n")
                val espresso = input.nextInt()
                val foam = input.nextInt()
                val milk = input.nextInt()
                val chocolate = input.nextInt()
                repository.prepareBeverage(beverage, espresso, foam, milk, chocolate)
            }
            else -> {
                repository.prepareBeverage(beverage)
            }
        }
        when(orderState){
            is BaseResult.Success ->{
                println("Please collect your ${orderState.order.name}")
                while (orderState.order.decorator.hasNext()){
                    val decoratorItem = orderState.order.decorator.next()
                    println("${decoratorItem.first} --> ${decoratorItem.second}" )
                }
            }
            is BaseResult.Error ->{
                println(orderState.msg)
            }
        }
    }else{
        println("Select a valid index")
    }

}
