package ru.netology

import org.junit.Test

import org.junit.Assert.*

class VkComissionKtTest {

    @Test
    fun transferComissionVkPay() {
        val transferAmount = 5_000__00
        val expectedResult = 0

        val result = transferComission(transferAmount = transferAmount)

        assertEquals(expectedResult, result)
    }

    @Test
    fun transferComissionMaestroAndMastercardWithoutComission() {
        val transferAmount = 5_000__00
        val cardType = CardType.MASTERCARD
        val expectedResult = 0

        val result = transferComission(cardType = cardType, transferAmount = transferAmount)

        assertEquals(expectedResult, result)
    }

    @Test
    fun transferComissionMaestroAndMastercardWithComission() {
        val transferAmount = 200__00
        val cardType = CardType.MASTERCARD
        val expectedResult = 21__20

        val result = transferComission(cardType = cardType, transferAmount = transferAmount)

        assertEquals(expectedResult, result)
    }

    @Test
    fun transferComissionMaestroAndMastercardWithComission2() {
        val monthTransfersAmount = 80_000__00
        val transferAmount = 200__00
        val cardType = CardType.MASTERCARD
        val expectedResult = 21__20

        val result = transferComission(
            cardType = cardType,
            monthTransfersAmount = monthTransfersAmount,
            transferAmount = transferAmount
        )

        assertEquals(expectedResult, result)
    }

    @Test
    fun transferComissionMaestroAndMastercardWithComission3() {
        val monthTransfersAmount = 70_000__00
        val transferAmount = 6_000__00
        val cardType = CardType.MASTERCARD
        val expectedResult = 26__00

        val result = transferComission(
            cardType = cardType,
            monthTransfersAmount = monthTransfersAmount,
            transferAmount = transferAmount
        )

        assertEquals(expectedResult, result)
    }

    @Test
    fun transferComissionVisaAndMirMin() {
        val transferAmount = 200__00
        val cardType = CardType.VISA
        val expectedResult = 35__00

        val result = transferComission(cardType = cardType, transferAmount = transferAmount)

        assertEquals(expectedResult, result)
    }

    @Test
    fun transferComissionVisaAndMir() {
        val transferAmount = 50_000__00
        val cardType = CardType.VISA
        val expectedResult = 375__00

        val result = transferComission(cardType = cardType, transferAmount = transferAmount)

        assertEquals(expectedResult, result)
    }

    @Test
    fun checkingLimitsVkPAYTest1() {
        val cardType = CardType.VK_PAY
        val monthTransfersAmount = 38_000__00
        val transferAmount = 5_000__00
        val expectedResult = false

        val result = checkingLimits(
            cardType = cardType,
            monthTransfersAmount = monthTransfersAmount,
            transferAmount = transferAmount
        )

        assertEquals(expectedResult, result)
    }

    @Test
    fun checkingLimitsVkPAYTest2() {
        val cardType = CardType.VK_PAY
        val monthTransfersAmount = 30_000__00
        val transferAmount = 20_000__00
        val expectedResult = false

        val result = checkingLimits(
            cardType = cardType,
            monthTransfersAmount = monthTransfersAmount,
            transferAmount = transferAmount
        )

        assertEquals(expectedResult, result)
    }

    @Test
    fun checkingLimitsVkPAYTest3() {
        val cardType = CardType.VK_PAY
        val monthTransfersAmount = 30_000__00
        val transferAmount = 10_000__00
        val expectedResult = true

        val result = checkingLimits(
            cardType = cardType,
            monthTransfersAmount = monthTransfersAmount,
            transferAmount = transferAmount
        )

        assertEquals(expectedResult, result)
    }

    @Test
    fun checkingLimitsOtherCardsTest1() {
        val cardType = CardType.MAESTRO
        val monthTransfersAmount = 580_000__00
        val transferAmount = 21_000__00
        val expectedResult = false

        val result = checkingLimits(
            cardType = cardType,
            monthTransfersAmount = monthTransfersAmount,
            transferAmount = transferAmount
        )

        assertEquals(expectedResult, result)
    }
    @Test
    fun checkingLimitsOtherCardsTest2() {
        val cardType = CardType.MAESTRO
        val monthTransfersAmount = 200_000__00
        val transferAmount = 151_000__00
        val expectedResult = false

        val result = checkingLimits(
            cardType = cardType,
            monthTransfersAmount = monthTransfersAmount,
            transferAmount = transferAmount
        )

        assertEquals(expectedResult, result)
    }

    @Test
    fun checkingLimitsOtherCardsTest3() {
        val cardType = CardType.MAESTRO
        val monthTransfersAmount = 450_000__00
        val transferAmount = 150_000__00
        val expectedResult = true

        val result = checkingLimits(
            cardType = cardType,
            monthTransfersAmount = monthTransfersAmount,
            transferAmount = transferAmount
        )

        assertEquals(expectedResult, result)
    }


    @Test
    fun visualSumTest() {
        val sum = 45__60
        val expectResult = "45 руб. 60 коп."

        val result = visualSum(sum)

        assertEquals(expectResult, result)
    }
}