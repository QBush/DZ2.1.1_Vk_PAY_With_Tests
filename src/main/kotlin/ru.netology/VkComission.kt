package ru.netology

const val MASTERCARD_AND_MAESTRO_PROCENT_COMISSION = 0.006
const val VISA_AND_MIR_PROCENT_COMISSION = 0.0075
const val MASTERCARD_AND_MAESTRO_LOWER_LIMIT = 300__00
const val MASTERCARD_AND_MAESTRO_UPPER_LIMIT = 75_000__00
const val VISA_AND_MIR_MINIMAL_COMISSION = 35__00
const val MASTERCARD_AND_MAESTRO_MINIMAL_COMISSION = 20__00
const val VK_PAY_DAY_LIMIT = 15_000__00
const val VK_PAY_MONTH_LIMIT = 40_000__00
const val OTHER_DAY_LIMIT = 150_000__00
const val OTHER_MONTH_LIMIT = 600_000__00

enum class CardType { MASTERCARD, MAESTRO, VISA, МИР, VK_PAY }

fun main() {
    var Comission1 = transferComission(CardType.MAESTRO, 80_000__00, 10_000__00)
    println("Комиссия: ${visualSum(Comission1)} \n")

}

@JvmOverloads
fun transferComission(cardType: CardType = CardType.VK_PAY, monthTransfersAmount: Int = 0, transferAmount: Int): Int {
    if (!checkingLimits(cardType, monthTransfersAmount, transferAmount)) {
        return 0
    } else {
        var comission = when (cardType) {
            CardType.MASTERCARD, CardType.MAESTRO -> mastercardAndMaestroComission(monthTransfersAmount, transferAmount)
            CardType.VISA, CardType.МИР -> visaAndMirComission(transferAmount)
            CardType.VK_PAY -> 0
        }
        return comission
    }

}

fun mastercardAndMaestroComission(monthTransfersAmount: Int, transferAmount: Int): Int {
    var comission = when {
        (monthTransfersAmount + transferAmount) in MASTERCARD_AND_MAESTRO_LOWER_LIMIT..MASTERCARD_AND_MAESTRO_UPPER_LIMIT -> 0
        (monthTransfersAmount + transferAmount) in 0..(MASTERCARD_AND_MAESTRO_LOWER_LIMIT - 1)
                || monthTransfersAmount > MASTERCARD_AND_MAESTRO_UPPER_LIMIT
        -> (transferAmount * MASTERCARD_AND_MAESTRO_PROCENT_COMISSION + MASTERCARD_AND_MAESTRO_MINIMAL_COMISSION).toInt()
        else -> (((monthTransfersAmount + transferAmount - MASTERCARD_AND_MAESTRO_UPPER_LIMIT) * MASTERCARD_AND_MAESTRO_PROCENT_COMISSION) + MASTERCARD_AND_MAESTRO_MINIMAL_COMISSION).toInt()
    }
    return comission
}

fun visaAndMirComission(transferAmount: Int): Int {
    var comission = (transferAmount * VISA_AND_MIR_PROCENT_COMISSION).toInt()
    if (comission < VISA_AND_MIR_MINIMAL_COMISSION) comission = VISA_AND_MIR_MINIMAL_COMISSION
    return comission
}

fun checkingLimits(cardType: CardType, monthTransfersAmount: Int, transferAmount: Int): Boolean {
    if (cardType == CardType.VK_PAY && (transferAmount > VK_PAY_DAY_LIMIT || transferAmount + monthTransfersAmount > VK_PAY_MONTH_LIMIT)) {
        println("Лимит переводов превышен")
        return false
    }
    if (cardType != CardType.VK_PAY && (transferAmount > OTHER_DAY_LIMIT || transferAmount + monthTransfersAmount > OTHER_MONTH_LIMIT)) {
        println("Лимит переводов превышен")
        return false
    }
    return true
}

fun visualSum(Sum: Int): String = "${Sum / 100} руб. ${Sum % 100} коп."