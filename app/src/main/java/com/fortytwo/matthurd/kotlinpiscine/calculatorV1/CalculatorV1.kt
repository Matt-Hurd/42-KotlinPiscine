package com.fortytwo.matthurd.kotlinpiscine.calculatorV1

import java.lang.Math.pow

enum class Operation {
    ADD, SUB, DIV, MULT
}

class CalculatorV1 {
    var totalValue = 0.0
    var holdingValue = 0.0
    var decimal = false
    var decimals = 0
    var previousOp: Operation? = null

    fun add(): Double {
        calculate()
        previousOp = Operation.ADD
        return totalValue
    }

    fun sub(): Double {
        calculate()
        previousOp = Operation.SUB
        return totalValue
    }

    fun div(): Double {
        calculate()
        previousOp = Operation.DIV
        return totalValue
    }

    fun mult(): Double {
        calculate()
        previousOp = Operation.MULT
        return totalValue
    }

    fun enableDecimal() {
        decimal = true
    }

    fun calculate(): Double {
        when (previousOp) {
            Operation.ADD -> totalValue += holdingValue
            Operation.SUB -> totalValue -= holdingValue
            Operation.MULT -> totalValue *= holdingValue
            Operation.DIV -> totalValue /= holdingValue
            null -> totalValue = holdingValue
        }
        decimal = false
        decimals = 0
        holdingValue = 0.0
        return totalValue
    }

    fun pressedDigit(digit: Int): Double {
        holdingValue = when (decimal) {
            true -> {
                decimals += 1
                holdingValue + digit.toDouble() / pow(10.0, decimals.toDouble()).toDouble() }
            false -> holdingValue * 10 + digit
        }
        return holdingValue
    }

    fun delete(): Double {
        holdingValue = 0.0
        totalValue = 0.0
        previousOp = null
        return holdingValue
    }
}