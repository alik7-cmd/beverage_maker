package common.data


data class BeverageOrder(
    val name: String,
    val decorator: Iterator<Pair<String, String>>,
    val price: Double
)