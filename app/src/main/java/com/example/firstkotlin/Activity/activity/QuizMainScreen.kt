package com.example.firstkotlin.Activity.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.example.firstkotlin.Activity.Interface.ApiInterface
import com.example.firstkotlin.Activity.Modals.QuestionModal
import com.example.firstkotlin.Activity.Modals.Result
import com.example.firstkotlin.Activity.RetrofitInfo.RetrofitConnection
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.ActivityQuizMainScreenBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizMainScreen : AppCompatActivity() {

    private lateinit var binding: ActivityQuizMainScreenBinding
    private var count = 0
    private var totalScore = 0
    private val userAnswers = mutableListOf<String?>() // List to store the user's selected answers
    private val isAnsweredCorrectly = mutableListOf<Boolean?>() // Track if the answer is correct or not

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val questionCount = intent.getStringExtra("question_value")?.toIntOrNull()
        val categoryId = intent.getStringExtra("category_id")?.toIntOrNull()
        val difficulty = intent.getStringExtra("difficulty")
        val questionType = intent.getStringExtra("question_type")

        if (questionCount != null && categoryId != null && difficulty != null && questionType != null) {

            if(questionType.equals("multiple")){
                getQuestion(questionCount, categoryId, difficulty, questionType)
            }
            else{
                getQuestion(questionCount,categoryId,difficulty,questionType)
            }

        } else {
            Toast.makeText(this, "Invalid quiz data", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun getQuestion(questionCount: Int, categoryId: Int, difficulty: String, questionType: String) {
        val apiInterface = RetrofitConnection.getQuestion().create(ApiInterface::class.java)
        val call = apiInterface.getQuestions(questionCount, categoryId, difficulty, questionType)

        call.enqueue(object : Callback<QuestionModal?> {
            override fun onResponse(call: Call<QuestionModal?>, response: Response<QuestionModal?>) {
                if (response.isSuccessful) {
                    response.body()?.let { sendQuestionData(it) } ?: showError("No questions found.")
                } else {
                    showError(response.message())
                }
            }

            override fun onFailure(call: Call<QuestionModal?>, t: Throwable) {
                showError(t.message)
            }
        })
    }

    private fun sendQuestionData(answer: QuestionModal) {
        val questionList = answer.results
        if (questionList.isNotEmpty()) {
            loadQuestion(questionList, count)
            setupNextButton(questionList)
        } else {
            showError("No data found.")
        }
    }

    private fun setupPreviousBtn(questionList: List<Result>) {
        if (count > 0) {
            count--
            loadQuestion(questionList, count)
            updateButtonVisibility(questionList)
        } else {
            Toast.makeText(this, "No previous question available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupNextButton(questionList: List<Result>) {
        binding.nexQuetn.setOnClickListener {
            val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId
            if (selectedRadioButtonId == -1) {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            } else {
                val selectedAnswer = findViewById<RadioButton>(selectedRadioButtonId)?.text.toString()
                checkAnswer(selectedAnswer, questionList, count)

                // Save the selected answer
                if (userAnswers.size <= count) {
                    userAnswers.add(selectedAnswer)  // Add the selected answer to the list
                } else {
                    userAnswers[count] = selectedAnswer // Update the answer at the current index
                }

                // Track correctness of the selected answer
                if (isAnsweredCorrectly.size <= count) {
                    isAnsweredCorrectly.add(selectedAnswer == questionList[count].correct_answer)
                } else {
                    isAnsweredCorrectly[count] = selectedAnswer == questionList[count].correct_answer
                }

                if (count < questionList.size - 1) {
                    count++
                    loadQuestion(questionList, count)
                } else {
                    binding.nexQuetn.visibility = View.GONE
                    binding.submitBtn.visibility = View.VISIBLE
//                    showFinalScore() // Show total score when the quiz ends
                }
                updateButtonVisibility(questionList)
            }
        }

        binding.submitBtn.setOnClickListener{
            navigateToScoreActivity()
        }

        binding.previousbtn.setOnClickListener {
            setupPreviousBtn(questionList)
        }
    }

    private fun updateButtonVisibility(questionList: List<Result>) {
        binding.previousbtn.visibility = if (count > 0) View.VISIBLE else View.GONE
        val isLastQuestion = count == questionList.size - 1
        val isAnswered = binding.radioGroup.checkedRadioButtonId != -1
        binding.nexQuetn.visibility = if (isLastQuestion && !isAnswered) View.VISIBLE else if (isLastQuestion) View.GONE else View.VISIBLE
    }

    private fun loadQuestion(questionList: List<Result>, index: Int) {
        if (index in questionList.indices) {
            val currentQuestion = questionList[index]
            val options = mutableListOf<String>().apply {
                addAll(currentQuestion.incorrect_answers)
                add(currentQuestion.correct_answer)
                shuffle()
            }
            val fq = HtmlCompat.fromHtml(questionList[index].question, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

            binding.question.text = fq

            if(questionList.get(index).type.equals("multiple")){
                binding.first.text = HtmlCompat.fromHtml(options[0], HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                binding.second.text = HtmlCompat.fromHtml(options[1], HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                binding.third.text = HtmlCompat.fromHtml(options[2], HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                binding.forth.text = HtmlCompat.fromHtml(options[3], HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
            }
            else{
                binding.first.text = HtmlCompat.fromHtml(options[0], HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                binding.second.text = HtmlCompat.fromHtml(options[1], HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
               binding.third.visibility = View.GONE
               binding.forth.visibility = View.GONE
            }

            binding.radioGroup.clearCheck()

            // Set previously selected answer if any
            if (userAnswers.size > index) {
                val selectedAnswer = userAnswers[index]
                if (selectedAnswer != null) {
                    val radioButton = when (selectedAnswer) {
                        options[0] -> binding.first
                        options[1] -> binding.second
                        options[2] -> binding.third
                        options[3] -> binding.forth
                        else -> null
                    }
                    radioButton?.isChecked = true
                }
            }

            binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
                findViewById<RadioButton>(checkedId)?.let { radioButton ->
                    radioButton.setTextColor(ContextCompat.getColor(this, R.color.light_textcolor))
                    radioButton.buttonTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(this, R.color.light_textcolor)
                    )
                }
            }
        } else {
            Toast.makeText(this, "No more questions", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkAnswer(selectedAnswer: String, questionList: List<Result>, index: Int) {
        val correctAnswer = questionList[index].correct_answer
        val isCorrect = selectedAnswer == correctAnswer

        // Ensure the lists have enough elements before accessing them
        if (isAnsweredCorrectly.size <= index) {
            isAnsweredCorrectly.add(isCorrect)  // Add the answer correctness status if not present
        } else {
            isAnsweredCorrectly[index] = isCorrect  // Update the correctness status
        }

        // Adjust the score if the answer changes
        if (isCorrect) {
            totalScore += 1
        } else if (isAnsweredCorrectly[index] != null && isAnsweredCorrectly[index] != isCorrect) {
            totalScore -= 1  // Subtract points if the answer was changed
        }

        // Show a message based on whether the answer was correct or not
        val message = if (isCorrect) "Correct Answer" else "Incorrect Answer"
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun showFinalScore() {
        Toast.makeText(this, "You scored $totalScore marks!", Toast.LENGTH_LONG).show()
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message ?: "An error occurred", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToScoreActivity() {
        val intent = Intent(this, Results::class.java)
        intent.putExtra("totalScore", totalScore)
        intent.putExtra("totalQuestions", userAnswers.size)
        startActivity(intent)
        finish()
    }

}



