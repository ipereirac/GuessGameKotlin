package com.ipereirac.guessgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var answer = 0
    var isGameOver = false
    var numOfAttempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startOver()
    }

    private fun generateAnswer() {
        answer = Random.nextInt(1, 25)
    }

    private fun startOver() {
        isGameOver = false
        numOfAttempts = 0
        generateAnswer()
        val answerTextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = "??"
        findViewById<Button>(R.id.buttonSubmit).isEnabled = true
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Guess 1 to 25"
        findViewById<EditText>(R.id.editTextGuess).text.clear()
    }

    fun btnStartOverTapped(view: android.view.View) {
        startOver()
    }

    fun btnSubmitTapped(view: android.view.View) {
        val guess = getUserGuess() ?: 0
        val textView = findViewById<TextView>(R.id.textView)
        if (guess !in 1..25) {
            textView.text = getString(R.string.guess_must_be_1_to_25)
            return
        }
        var message = ""
        numOfAttempts++

        if (guess == answer) {
            isGameOver = true
            val answerTextView = findViewById<TextView>(R.id.answer)
            answerTextView.text = answer.toString()
            message = "Correct! Guess(es): $numOfAttempts"
            findViewById<Button>(R.id.buttonSubmit).isEnabled = false

        } else {
            message = if (guess < answer) "Too low!" else "Too high!"
        }
        textView.text = message
    }

    private fun getUserGuess(): Int? {
        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        val guess = editTextGuess.text.toString()
        return try {
            guess.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }
}