package presentation

import common.data.Beverage
import common.ServiceLocator
import common.data.BeverageType
import common.data.CoffeeType
import java.util.*
import kotlin.system.exitProcess

private lateinit var beverage: Beverage
val input = Scanner(System.`in`)
fun main() {

    val viewModel = BeverageViewModel(ServiceLocator.getBeverageRepository(),
        ServiceLocator.getPaymentService())
    print("Enter card number: ")
    val cardId = input.nextLine()

    print("Enter password: ")
    val password = input.nextLine()
    viewModel.makePayment(cardId, password)
    observe(viewModel)
    observe(viewModel)

    when(beverage.type){
        BeverageType.COFFEE ->{
            println("Please select your options on your ${beverage.name}")

            print("Select Espresso shot(1-5): ")
            val espresso = input.nextInt()

            print("Select Milk(1-5): ")
            val milk = input.nextInt()
            var chocolate = 0
            var foam = 0

            when(beverage.coffeeType){
                CoffeeType.MOCHA ->{
                    print("Select Chocolate(1-5): ")
                    chocolate = input.nextInt()
                }

                CoffeeType.CAPPUCCINO ->{
                    print("Select Foam(1-5): ")
                    foam = input.nextInt()
                }

                else -> {}
            }

            viewModel.prepareBeverageBy(beverage, espresso, foam, milk, chocolate)
        }
        else -> {
            viewModel.prepareBeverageBy(beverage)
        }
    }
    println()
    observe(viewModel)
}

private fun observe(viewModel: BeverageViewModel){
    when(val state = viewModel.observer.subject.state){
        is BeverageMachineUiState.BeverageListSuccess -> {
            val emoji = "\uD83C\uDF5C"
            println("-------------------")
            state.listOrBeverage.forEachIndexed { index, beverage ->
                println(" ${index+1} \n Name: ${beverage.name} \nPrice: ${beverage.price} $emoji")
                println("-------------------")
            }
            println()
            print("Please select your beverage: ")
            val index = input.nextInt()
            beverage = state.listOrBeverage[index-1]
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
        BeverageMachineUiState.Init -> {}
        BeverageMachineUiState.PaymentFailed -> {
            println("Payment failed")
            exitProcess(0)
        }
        BeverageMachineUiState.PaymentSuccess -> {viewModel?.getAllBeverage()}
    }
}
