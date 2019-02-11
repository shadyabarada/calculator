package com.shadya.shadyascalculator.calculator

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface CalculatorMvp {
    interface Presenter : MvpPresenter<View> {
        fun validateInput(equation: String, input: String)

        fun getResult(equation: String)
    }

    interface View : MvpView {
        fun updateEquation(input: String)

        fun showResult(result: String)
    }
}