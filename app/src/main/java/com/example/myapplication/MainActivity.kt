package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.cal_button).setOnClickListener {
            cal(it)
        }

        findViewById<TextView>(R.id.ans_car_loan_result).setOnClickListener {
            updateEdit(it)
        }
    }
    private fun cal(view: View) {
        val editPrice = findViewById<EditText>(R.id.editPrice)
        val editDownPay = findViewById<EditText>(R.id.editDownPay)
        val editPeriod = findViewById<EditText>(R.id.editPeriod)
        val editInterest = findViewById<EditText>(R.id.editInterest)

        val carLoan = findViewById<TextView>(R.id.ans_car_loan_result)
        val carInterestRate = findViewById<TextView>(R.id.ans_interest)
        val repayment = findViewById<TextView>(R.id.ans_repayment)

        val price = editPrice.text.toString()
        val downPay = editDownPay.text.toString()
        val period = editPeriod.text.toString()
        val interest = editInterest.text.toString()

        val carLoanNum = price.toDouble() - downPay.toDouble()
        carLoan.text = "Car Loan Amount $carLoanNum"
        carLoan.visibility = View.VISIBLE

        val carInterest = carLoanNum.toDouble() * period.toDouble() * interest.toDouble()
        carInterestRate.text = "Car Interest Amount $carInterest"

        val mon_repayment = (carLoanNum + carInterest) / period.toDouble() / 12
        repayment.text = "Car Repayment :%.2f".format(mon_repayment)

        // don't the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateEdit (view: View) {
        val editCarPrice = findViewById<EditText>(R.id.editPrice)
        val editDownPay = findViewById<EditText>(R.id.editDownPay)

        // Set the focus to the edit text.
        editCarPrice.requestFocus()
        editDownPay.requestFocus()

        // Show the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editCarPrice, 0)
        imm.showSoftInput(editDownPay, 0)
    }
}
