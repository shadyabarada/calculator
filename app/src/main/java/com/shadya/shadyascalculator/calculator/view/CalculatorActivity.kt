package com.shadya.shadyascalculator.calculator.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.shadya.shadyascalculator.R
import com.shadya.shadyascalculator.calculator.CalculatorMvp
import com.shadya.shadyascalculator.calculator.presenter.CalculatorPresenter
import kotlinx.android.synthetic.main.calculator_layout.*

class CalculatorActivity : MvpActivity<CalculatorMvp.View, CalculatorMvp.Presenter>(), CalculatorMvp.View {
    var equation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_layout)
    }

    override fun createPresenter(): CalculatorMvp.Presenter {
        return CalculatorPresenter()
    }

    fun onNumberOperationClicked(view: View) {
        var text = view as TextView
        presenter.validateInput(equation, text.text.toString())
    }

    override fun updateEquation(input: String) {
        equation = equation + input
        calculator_text.text = equation
        clear.visibility = View.GONE
        backspace.visibility = View.VISIBLE
    }

    fun onBackSpacePressed(view: View) {
        if (!equation.isBlank()) {
            equation = equation.substring(0, equation.length - 1)
            calculator_text.text = equation
        }
    }

    fun onEqualPressed(view: View) {
        presenter.getResult(equation)
    }

    override fun showResult(result: String) {
        equation = result
        calculator_text.text = result
        clear.visibility = View.VISIBLE
        backspace.visibility = View.GONE
    }

    fun clear(view: View) {
        calculator_text.text = "0"
        equation = ""
    }

}
