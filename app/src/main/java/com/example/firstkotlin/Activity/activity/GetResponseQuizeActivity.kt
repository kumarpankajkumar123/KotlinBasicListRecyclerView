package com.example.firstkotlin.Activity.activity

import android.content.Intent
import android.os.Bundle
import android.text.BoringLayout
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.ActivityGetResponseQuizeBinding
import kotlin.math.log
import kotlin.properties.Delegates

class GetResponseQuizeActivity : AppCompatActivity() {

    lateinit var binding: ActivityGetResponseQuizeBinding

    lateinit var category: List<String>
    lateinit var diff: List<String>
    lateinit var type: List<String>
    lateinit var encoding: List<String>
    lateinit var map: Map<String, Int>
    var category_mapping by Delegates.notNull<Int>()
    lateinit var diffItem: String
    lateinit var multiple: String
    var int_val: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetResponseQuizeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        diffItem = "Select Difficulty"
        multiple = "Choice Type"
        category_mapping = 0
        val strname = intent.getStringExtra("name")
        Log.e("name", "" + strname)

        category = arrayListOf(
            "Select Category",
            "General Knowledge",
            "Books",
            "Film",
            "Music",
            "Musicals & Theatres",
            "Television",
            "Video Games",
            "Board Games",
            "Science & Nature",
            "Computers",
            "Mathematics",
            "Mythology",
            "Sports",
            "Geography",
            "History",
            "Politics",
            "Art",
            "Celebrities",
            "Animals",
            "Vehicles",
            "Comics",
            "Gadgets",
            "Anime & Manga",
            "Cartoon & Animations"
        )
        diff = arrayListOf("Select Difficulty", "Easy", "Medium", "Hard")
        type = arrayListOf("Choice Type", "True/False", "Multiple Choice")
        encoding = arrayListOf(
            "Choice Encoding",
            "Legacy URL Encoding",
            "URL Encoding(RFC 3986",
            "Base64 Encoding"
        )
        map = mapOf(
            "General Knowledge" to 9,
            "Books" to 10,
            "Film" to 11,
            "Music" to 12,
            "Musicals & Theatres" to 13,
            "Television" to 14,
            "Video Games" to 15,
            "Board Games" to 16,
            "Science & Nature" to 17,
            "Computers" to 18,
            "Mathematics" to 19,
            "Mythology" to 20,
            "Sports" to 21,
            "Geography" to 22,
            "History" to 23,
            "Politics" to 24,
            "Art" to 25,
            "Celebrities" to 26,
            "Animals" to 27,
            "Vehicles" to 28,
            "Comics" to 29,
            "Gadgets" to 30,
            "Anime & Manga" to 31,
            "Cartoon & Animations" to 32
        )

        if (binding.categorySpinner != null) {
            val adapter = ArrayAdapter(
                this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                category
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.categorySpinner.adapter = adapter
        }
        if (binding.difficultySpinner != null) {
            val adapter = ArrayAdapter(
                this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                diff
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.difficultySpinner.adapter = adapter
        }
        if (binding.choiceSpinner != null) {
            val adapter = ArrayAdapter(
                this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                type
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.choiceSpinner.adapter = adapter
        }

        var isSpinnerInitialized = false
        var isSpinnerInitializedd = false
        var isSpinnerInitializedt = false
        binding.categorySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (isSpinnerInitialized) {
                        val categoryItem = category.get(position)
                        if (categoryItem.equals("Select Category")) {
                            Toast.makeText(
                                this@GetResponseQuizeActivity,
                                "select category",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            getCategoryId(categoryItem)
                        }
                    } else {
                        isSpinnerInitialized = true
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        binding.difficultySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (isSpinnerInitializedd) {
                        val difficultItem = diff.get(position)
                        if (difficultItem.equals("Select Difficulty")) {
                            Toast.makeText(
                                this@GetResponseQuizeActivity,
                                "select difficulty",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            diffItem = difficultItem.lowercase()
                            Log.e("difficulty item", ":=" + difficultItem.lowercase())
                        }
                    } else {
                        isSpinnerInitializedd = true
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        binding.choiceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (isSpinnerInitializedt) {
                    val typeItem = type.get(position)
                    if (typeItem.equals("Choice Type")) {
                        Toast.makeText(
                            this@GetResponseQuizeActivity,
                            "select type of question",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Log.e("type item", ":=" + typeItem.lowercase())
                        if (typeItem.equals("Multiple Choice")) {
                            multiple = "multiple"
                            Log.e("Multiple", ":=" + multiple)
                        } else {
                            multiple = "boolean"
                            Log.e("true/false", ":=" + multiple)
                        }
                    }
                } else {
                    isSpinnerInitializedt = true
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.nextBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val question_str = binding.questionSpinner.text.toString().trim()
                Log.e("first value", ": $question_str")

                if (checkvaluenull(question_str)) {
                    val int_val = question_str.toInt()
                    nextaction(int_val, category_mapping, diffItem, multiple)
                }
            }
        })
    }

    fun getCategoryId(c_value: String) {
        category_mapping = map.get(c_value)!!
        Log.e("this is the category", "code" + category_mapping)
    }

    fun checkvaluenull(question_str: String): Boolean {
        // Check if the input is empty
        if (question_str.isEmpty()) {
            binding.questionSpinner.error = "Please enter the number of questions"
            binding.questionSpinner.requestFocus()
            return false
        }

        // Convert to integer safely
        val int_val = question_str.toIntOrNull()
        if (int_val == null || int_val <= 0) {
            binding.questionSpinner.error = "Enter a valid positive number"
            binding.questionSpinner.requestFocus()
            return false
        }

        // Check range constraint
        if (int_val > 50) {
            Toast.makeText(
                this,
                "Enter a number of questions less than or equal to 50",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // Log the value if valid
        Log.e("question_int value", ": $int_val")
        return true
    }


    fun nextaction(qn: Int, CId: Int, diffItem: String, Type: String) {

        if (qn == 0 || CId == null || diffItem.equals("Select Difficulty") || Type.equals("Choice Type")) {
            Toast.makeText(this@GetResponseQuizeActivity,"select all fields",Toast.LENGTH_SHORT).show()
            Log.e("else part",":= select all fields");
        }
        else{
           val intent = Intent(this@GetResponseQuizeActivity, QuizMainScreen::class.java)
            intent.putExtra("question_value",qn.toString())
            intent.putExtra("category_id",CId.toString())
            intent.putExtra("difficulty",diffItem)
            intent.putExtra("question_type",Type)

            Log.e("question_value", ":" + qn)
            Log.e("category_id", ":" + CId)
            Log.e("difficulty", ":" + diffItem)
            Log.e("question_type", ":" + Type)
            startActivity(intent)
        }

    }
}