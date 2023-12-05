package water

import common.data.Decorator

class HotWaterDecorator : Decorator() {
    override fun toString() = "Size -> $size\n" +
            "Temperature -> $temperature"
}