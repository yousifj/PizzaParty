package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

const val SLICES_PER_PIZZA = 8
private const val KEY_TOTAL_PIZZAS = "totalPizzas"

class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup
    private var totalPizzas = 0

    //overrides the onCreate function to add new filed to the screen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)

        // Restore state
        if (savedInstanceState != null) {
            totalPizzas = savedInstanceState.getInt(KEY_TOTAL_PIZZAS)
            displayTotal()
        }
    }
    //save state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TOTAL_PIZZAS, totalPizzas)
    }

    /**
     * Function to compute the correct number of pizzas
     * according to the user input
     * and to display it on the screen
     */
    fun calculateClick(view: View) {

        // Get user input from the EditText field
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the input into an integer
        val numAttend = numAttendStr.toInt()

        // Assign the number of slices depending on how hungry the user is
        val slicesPerPerson = when (howHungryRadioGroup.checkedRadioButtonId) {
            //if light is pressed then 2 slices per person
            R.id.light_radio_button -> 2
            //if medium is pressed then 3 slices per person
            R.id.medium_radio_button -> 3
            //else if Ravenous then 4 slices per person
            else -> 4
        }

        // calculates the total pizzas needed for the number of people provided
        totalPizzas = ceil(numAttend * slicesPerPerson / SLICES_PER_PIZZA.toDouble()).toInt()
        //Display the number of pizzas on the screen
        displayTotal()
    }
    //Display the number of pizzas on the screen
    private fun displayTotal() {
        numPizzasTextView.text = "Total pizzas: $totalPizzas"
    }
}