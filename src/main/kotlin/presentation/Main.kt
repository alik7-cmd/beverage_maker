package presentation

import common.ResourceServiceImpl
import common.data.BaseResult
import common.data.Beverage
import common.BeverageRepositoryImpl
import java.util.*

fun main() {
    /*val viewModel = BeverageViewModel(BeverageRepositoryImpl(ResourceServiceImpl()))
    viewModel.getAllBeverage()
    val state = viewModel.observer.subject.state
    observe(state)
    viewModel.prepareBeverageOrderBy(beverage)
    observe(state)*/
    val input = Scanner(System.`in`)


    val repository = BeverageRepositoryImpl(ResourceServiceImpl())
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
        when(val orderState = repository.prepareBeverage(beverage)){
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
