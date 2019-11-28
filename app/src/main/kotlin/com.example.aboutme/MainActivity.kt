package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myName = MyName("Arman")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName
        binding.doneButton.setOnClickListener(::addNickname)
    }

    private fun addNickname(view: View) {
        binding.apply {
            // View is not updated if invalidate isn't called
            invalidateAll()
            nicknameEdit.let {
                myName?.nickname = it.text.toString()
                it.visibility = View.GONE
                nicknameText.visibility = View.VISIBLE
            }
        }
        view.visibility = View.GONE

        // Hide the keyboard
        hideKeyboard(view.windowToken)
    }
}

private fun Context.hideKeyboard(windowToken: IBinder) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(windowToken, 0)
}
