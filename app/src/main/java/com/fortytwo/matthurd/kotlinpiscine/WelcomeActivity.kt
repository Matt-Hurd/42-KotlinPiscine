package com.fortytwo.matthurd.kotlinpiscine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import android.content.Intent
import butterknife.OnClick
import com.fortytwo.matthurd.kotlinpiscine.calculatorV1.CalculatorActivity
import com.fortytwo.matthurd.kotlinpiscine.intra.IntraActivity

class WelcomeActivity : AppCompatActivity() {

    @BindView(R.id.welcome_text_view) lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)
        ButterKnife.bind(this)
    }

    override fun onStart() {
        super.onStart()
        textView.text = "ayylmao"
    }

    @OnClick(R.id.button_calculatorv1)
    fun gotoCalculator() {
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.button_intra_lookup)
    fun gotoIntraLookup() {
        val intent = Intent(this, IntraActivity::class.java)
        startActivity(intent)
    }
}
