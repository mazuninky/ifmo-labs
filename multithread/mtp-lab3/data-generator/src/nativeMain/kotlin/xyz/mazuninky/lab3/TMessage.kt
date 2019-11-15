package xyz.mazuninky.lab3

import kotlinx.cinterop.addressOf
import kotlinx.cinterop.toCValues
import kotlinx.cinterop.usePinned
import platform.posix.STDOUT_FILENO
import platform.posix.stdout

sealed class TMessage

data class Fibonacci(val n: Int) : TMessage()
data class Pow(val base: Float, val n: Int) : TMessage()
data class BubbleSort(val numbers: List<Float>) : TMessage()
object Stop : TMessage()

//data class TMessageBytes(val type: Byte, val size: Long,
//                         val data: ByteArray = ByteArray(0))
//
//fun TMessage.toBytes(): TMessageBytes {
//    return when (this) {
//        is Fibonacci -> TMessageBytes(0, 0)
//    }
//}

fun write(message: TMessage) {
    val byteArray: ByteArray
    when (message) {
        is Fibonacci -> {
            byteArray = ByteArray(UByte.SIZE_BYTES
                    + ULong.SIZE_BYTES + Int.SIZE_BYTES)
            byteArray.setUByteAt(0, 0.toUByte())
            byteArray.setULongAt(UByte.SIZE_BYTES, Int.SIZE_BYTES.toULong())
            byteArray.setIntAt(UByte.SIZE_BYTES + ULong.SIZE_BYTES, message.n)
        }
        is Stop -> {
            byteArray = ByteArray(UByte.SIZE_BYTES + ULong.SIZE_BYTES)
            byteArray.setUByteAt(0, 3.toUByte())
            byteArray.setULongAt(UByte.SIZE_BYTES, 0.toULong())
        }
//        is Pow -> {
//
//        }
        else -> TODO()
    }

    byteArray.usePinned {
        platform.posix.write(STDOUT_FILENO, it.addressOf(0), it.get().size.toULong())
    }
}