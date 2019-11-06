package lab2

inline class Ruble(val value: Float)

sealed class Currency(val exchangeRateToRub: Float)

object Dollar : Currency(64.66f)
object Euro : Currency(71f)
object Yuan : Currency(0.11f)

data class CurrencyContainer(val value: Float, val currency: Currency) {
    fun toRubli(): Ruble {
        return Ruble(value * currency.exchangeRateToRub)
    }
}

fun Ruble.toCurrency(currency: Currency): CurrencyContainer {
    val value = value * 1 / currency.exchangeRateToRub
    return CurrencyContainer(value, currency)
}

fun dollar(value: Float): CurrencyContainer =
        CurrencyContainer(value, Dollar)

fun euro(value: Float): CurrencyContainer =
        CurrencyContainer(value, Euro)

fun yuan(value: Float): CurrencyContainer =
        CurrencyContainer(value, Yuan)

class MoneyConverter {
    fun convertToRubli(container: CurrencyContainer): Ruble {
        return container.toRubli()
    }

    fun convertRubliTo(rub: Ruble, currency: Currency): CurrencyContainer {
        return rub.toCurrency(currency)
    }
}
