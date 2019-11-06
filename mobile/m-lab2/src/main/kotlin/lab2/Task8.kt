package lab2

open class AirBusA320 {
    private val speed = 5
    private val length = 0

    fun speed(): Int {
        return speed
    }

    fun length(): Int {
        return length
    }
}

class AirBusA380 : AirBusA320() {
    private val capacity = 60

    fun capacity(): Int {
        return capacity
    }
}
