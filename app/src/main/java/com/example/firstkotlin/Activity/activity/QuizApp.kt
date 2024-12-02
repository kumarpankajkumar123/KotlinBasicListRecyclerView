package com.example.firstkotlin.Activity.activity

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.firstkotlin.databinding.ActivityQuizAppBinding
import kotlin.properties.Delegates

class QuizApp : AppCompatActivity() {

    lateinit var binding : ActivityQuizAppBinding
    var isLoggin by Delegates.notNull<Boolean>()
    lateinit var sharedfrefferece: SharedPreferences
    var PICK_IMAGE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedfrefferece = getSharedPreferences("myProfile", MODE_PRIVATE)
        isLoggin=false

        val editor = sharedfrefferece.edit()

       val url =  sharedfrefferece.getString("profile_image_uri",null)
        if(url != null){
            Glide.with(this).load(Uri.parse(url)).into(binding.profileImage)
        }

        binding.startbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val strname = binding.editname.text.toString()
                Log.e("Quizapp name",""+strname)

                if(strname.isEmpty() || strname.isNullOrBlank()){
                    Toast.makeText(this@QuizApp,"please enter name",Toast.LENGTH_SHORT).show()
                }
                else{
                    isLoggin = true
                    editor.putString("name",strname);
                    editor.putBoolean("isLogged",isLoggin)
                    editor.apply()
                    editor.commit()
                    intent = Intent(this@QuizApp,GetResponseQuizeActivity::class.java)
                    intent.putExtra("name",strname)
                    startActivity(intent)
                }
            }
        })

        binding.uploadImage.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, this.PICK_IMAGE)
        }

        requestMediaPermission()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            selectedImageUri?.let { uri ->
                // Load the selected image into the CircleImageView
                Glide.with(this).load(uri).into(binding.profileImage)

                // Save the image URI in SharedPreferences
                sharedfrefferece.edit().putString("profile_image_uri", uri.toString()).apply()
            }
        }
    }
    private fun requestMediaPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_MEDIA_IMAGES), 101)
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
            }
        }
    }

}