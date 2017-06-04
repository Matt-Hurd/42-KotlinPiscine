package com.fortytwo.matthurd.kotlinpiscine.calculatorV1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.fortytwo.matthurd.kotlinpiscine.R

class CalculatorActivity : AppCompatActivity() {

    @BindView(R.id.calcResult) lateinit var calcResult: TextView
    var calculator = CalculatorV1()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_v1)
        ButterKnife.bind(this)
    }

    override fun onStart() {
        super.onStart()
    }

    @OnClick(R.id.button0)
    fun pressed0() {
        calcResult.text = calculator.pressedDigit(0).toString()
    }

    @OnClick(R.id.button1)
    fun pressed1() {
        calcResult.text = calculator.pressedDigit(1).toString()
    }

    @OnClick(R.id.button2)
    fun pressed2() {
        calcResult.text = calculator.pressedDigit(2).toString()
    }

    @OnClick(R.id.button3)
    fun pressed3() {
        calcResult.text = calculator.pressedDigit(3).toString()
    }

    @OnClick(R.id.button4)
    fun pressed4() {
        calcResult.text = calculator.pressedDigit(4).toString()
    }

    @OnClick(R.id.button5)
    fun pressed5() {
        calcResult.text = calculator.pressedDigit(5).toString()
    }

    @OnClick(R.id.button6)
    fun pressed6() {
        calcResult.text = calculator.pressedDigit(6).toString()
    }

    @OnClick(R.id.button7)
    fun pressed7() {
        calcResult.text = calculator.pressedDigit(7).toString()
    }

    @OnClick(R.id.button8)
    fun pressed8() {
        calcResult.text = calculator.pressedDigit(8).toString()
    }

    @OnClick(R.id.button9)
    fun pressed9() {
        calcResult.text = calculator.pressedDigit(9).toString()
    }

    @OnClick(R.id.buttonMinus)
    fun pressedMinus() {
        calcResult.text = calculator.sub().toString()
    }

    @OnClick(R.id.buttonPlus)
    fun pressedPlus() {
        calcResult.text = calculator.add().toString()
    }

    @OnClick(R.id.buttonDivide)
    fun pressedDivide() {
        calcResult.text = calculator.div().toString()
    }

    @OnClick(R.id.buttonMultiply)
    fun pressedMultiply() {
        calcResult.text = calculator.mult().toString()
    }

    @OnClick(R.id.buttonDecimal)
    fun pressedDecimal() {
        calculator.enableDecimal()
    }

    @OnClick(R.id.buttonEquals)
    fun pressedEquals() {
        calcResult.text = calculator.calculate().toString()
    }

    @OnClick(R.id.buttonDel)
    fun pressedDelete() {
        calcResult.text = calculator.delete().toString()
    }

}
