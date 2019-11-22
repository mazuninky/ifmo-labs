package xyz.mazuninky.lab3

import kotlinx.cinterop.addressOf
import kotlinx.cinterop.toCValues
import kotlinx.cinterop.usePinned
import platform.posix.STDOUT_FILENO
import platform.posix.stdout

sealed class TMessage

data class Fibonacci(val n: UInt) : TMessage()
data class Pow(val base: Int, val n: UInt) : TMessage()
data class BubbleSort(val numbers: List<ULong>) : TMessage()
object Stop : TMessage()

//data class TMessageBytes(val type: Byte, val size: Long,
//                         val data: ByteArray = ByteArray(0))
//
//fun TMessage.toBytes(): TMessageBytes {
//    return when (this) {
//        is Fibonacci -> TMessageBytes(0, 0)
//    }
//}

@UseExperimental(ExperimentalUnsignedTypes::class)
fun write(message: TMessage) {
    val byteArray: ByteArray
    when (message) {
        is Fibonacci -> {
            byteArray = ByteArray(UByte.SIZE_BYTES + ULong.SIZE_BYTES + Int.SIZE_BYTES)
            byteArray.setUByteAt(0, 0.toUByte())
            byteArray.setULongAt(UByte.SIZE_BYTES, Int.SIZE_BYTES.toULong())
            byteArray.setUIntAt(UByte.SIZE_BYTES + ULong.SIZE_BYTES, message.n)
        }
        is Pow -> {
            byteArray = ByteArray(UByte.SIZE_BYTES + ULong.SIZE_BYTES + Int.SIZE_BYTES + UInt.SIZE_BYTES)
            byteArray.setUByteAt(0, 1.toUByte())
            byteArray.setULongAt(UByte.SIZE_BYTES, (Int.SIZE_BYTES + UInt.SIZE_BYTES).toULong())
            byteArray.setIntAt(UByte.SIZE_BYTES + ULong.SIZE_BYTES, message.base)
            byteArray.setUIntAt(UByte.SIZE_BYTES + ULong.SIZE_BYTES + Int.SIZE_BYTES, message.n)
        }
        is BubbleSort -> {
            byteArray = ByteArray(UByte.SIZE_BYTES + ULong.SIZE_BYTES + ULong.SIZE_BYTES * message.numbers.size)
            byteArray.setUByteAt(0, 2.toUByte())
            byteArray.setULongAt(UByte.SIZE_BYTES, (ULong.SIZE_BYTES * message.numbers.size).toULong())
            message.numbers.forEachIndexed { index, uLong ->
                byteArray.setULongAt(UByte.SIZE_BYTES + ULong.SIZE_BYTES + index * ULong.SIZE_BYTES, uLong)
            }
        }
        is Stop -> {
            byteArray = ByteArray(UByte.SIZE_BYTES + ULong.SIZE_BYTES)
            byteArray.setUByteAt(0, 3.toUByte())
            byteArray.setULongAt(UByte.SIZE_BYTES, 0.toULong())
        }
    }

    byteArray.usePinned {
        platform.posix.write(STDOUT_FILENO, it.addressOf(0), it.get().size.toULong())
    }
}