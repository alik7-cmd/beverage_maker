package presentation

import common.ResourceServiceImpl
import common.data.BaseResult
import common.data.Beverage
import common.factory.BeverageRepositoryImpl

lateinit var beverage: Beverage
fun main() {
    /*val viewModel = BeverageViewModel(BeverageRepositoryImpl(ResourceServiceImpl()))
    viewModel.getAllBeverage()
    val state = viewModel.observer.subject.state
    observe(state)
    viewModel.prepareBeverageOrderBy(beverage)
    observe(state)*/

    val repository = BeverageRepositoryImpl(ResourceServiceImpl())
    var list : List<Beverage> = emptyList()
    when(val response = repository.getAllBeverage()){
        is BaseResult.Success ->{
            list = response.order
            list.forEach {
                println("The name of the beverage is ${it.name} and price is ${it.price}")
            }
        }
        is BaseResult.Error ->{
            println(response.msg)
        }
    }

    println()

    when(val orderState = repository.prepareBeverage(list[0])){
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


}

private fun observe(state: BeverageMachineUiState){
    when(state){
        is BeverageMachineUiState.BeverageListSuccess -> {
            state.listOrBeverage.forEach {
                println("The name of the beverage is ${it.name} and price is ${it.price}")
            }
            beverage = state.listOrBeverage[1]

        }

        is BeverageMachineUiState.BeverageOrderSuccess ->{
            println("Please collect your ${state.order.name}")
            while (state.order.decorator.hasNext()){
                val decoratorItem = state.order.decorator.next()
                println("${decoratorItem.first} --> ${decoratorItem.second}" )
            }
        }

        is BeverageMachineUiState.Error ->{
            println(state.msg)
        }

        BeverageMachineUiState.Loading -> {}
    }
}