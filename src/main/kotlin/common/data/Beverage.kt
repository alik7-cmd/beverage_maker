package common.data

import AppConstant


open class Beverage(
    val type: BeverageType,
    val name : String,
    val price: Double,
    val image : String = AppConstant.IMAGE_URL,
    val currency : Currency = Currency.EURO
)