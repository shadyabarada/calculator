package com.shadya.shadyascalculator.calculator.presenter

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.shadya.shadyascalculator.calculator.CalculatorMvp
import com.shadya.shadyascalculator.calculator.utils.Parsers

class CalculatorPresenter : MvpBasePresenter<CalculatorMvp.View>(), CalculatorMvp.Presenter {
    override fun validateInput(equation: String, input: String) {
        if (equation.isBlank()) {
            if (!(input.equals("+") || input.equals("/") || input.equals("x"))) {
                if (isViewAttached) {
                    view.updateEquation(input)
                }

            }
        } else {
            if (isViewAttached) {
                view.updateEquation(input)
            }
        }
    }

    override fun getResult(equation: String) {
        var result = ""
        var tobeComputed = equation
        if (!tobeComputed.isBlank() && tobeComputed.contains("x")) {
            tobeComputed = tobeComputed.replace("x", "*")
        }
        if (!tobeComputed.isBlank()) {
            result = Parsers.parseAndCompute(tobeComputed).expression.toString()

        }
        if(isViewAttached)
        {
            view.showResult(result)
        }
    }
}