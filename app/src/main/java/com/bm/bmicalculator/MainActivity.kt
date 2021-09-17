package com.bm.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import org.w3c.dom.Text
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val imageBoy = findViewById<ImageView>(R.id.Image_boy)
        val imageGirl = findViewById<ImageView>(R.id.Image_girl)

        val weight = findViewById<EditText>(R.id.weight_value)
        val height = findViewById<EditText>(R.id.Height_value)

        val calculateBtn = findViewById<Button>(R.id.Calculate_Button)

        val bmi = findViewById<TextView>(R.id.bmi)
        val bmiStatus = findViewById<TextView>(R.id.bmi_sts)
        val bmiView = findViewById<LinearLayout>(R.id.BmI_view)

        val CalAgain = findViewById<TextView>(R.id.Calculate_again)


        imageBoy.setOnClickListener {
            imageBoy.setImageResource(R.drawable.ic_boy)
            imageGirl.setImageResource(R.drawable.ic_girl_blure)
        }

        imageGirl.setOnClickListener {
            imageGirl.setImageResource(R.drawable.ic_girl)
            imageBoy.setImageResource(R.drawable.ic_boy_blure)
        }

        calculateBtn.setOnClickListener {
            var Weightvalue = 0.0
            var Heightvalue = 0.0
            if (weight.text.toString().isNotEmpty()) {
                Weightvalue = weight.text.toString().toDouble()
            }

            if (height.text.toString().isNotEmpty()) {
                Heightvalue = (height.text.toString().toDouble()/100)
            }
            if (Weightvalue > 0.0 && Heightvalue > 0.0) {
                val bmiValue = String.format("%.2f", Weightvalue / Heightvalue.pow(2))
                bmi.text = bmiValue
                bmiStatus.text = bmiStatusValue(Weightvalue / Heightvalue.pow(2))
                bmiView.visibility = VISIBLE
                calculateBtn.visibility = GONE
            } else
                Toast.makeText(this, "Please Enter Weight and Height values!", Toast.LENGTH_LONG)
                    .show()
        }
        CalAgain.setOnClickListener {
            bmiView.visibility = GONE
            calculateBtn.visibility = VISIBLE
            weight.text.clear()
            height.text.clear()
            weight.requestFocus()

        }
    }

    private fun bmiStatusValue(bmi: Double): String {
        lateinit var bmiStatus: String
        if (bmi < 18.5)
            bmiStatus = "Underweight"
        else if (bmi >= 18.5 && bmi < 25)
            bmiStatus = "Normal"
        else if (bmi > 25 && bmi < 30)
            bmiStatus = "Overweight"
        else if (bmi > 30)
            bmiStatus = "Over Weight"
        return bmiStatus

    }
}