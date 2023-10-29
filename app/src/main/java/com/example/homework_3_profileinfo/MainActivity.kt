package com.example.homework_3_profileinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.homework_3_profileinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    fun isInputEmpty(firstName: String,
                     lastname: String,
                     email: String,
                     username: String,
                     age: String,
                     phone: String): String {
        return if(firstName.isEmpty() || lastname.isEmpty() || email.isEmpty() || username.isEmpty()
            || age.isEmpty() || phone.isEmpty()) {
            "You have to fill all inputs!"
        } else {
            ""
        }
    }
    fun checkPhone(phone: String): String {
        val regPhone = "(?:([+]\\d{1,4})[-.\\s]?)?(?:[(](\\d{1,3})[)][-.\\s]?)?(\\d{1,4})[-.\\s]?(\\d{1,4})[-.\\s]?(\\d{1,9})"
        return if(!phone.matches(regPhone.toRegex())) {
            "Error! Phone is invalid"
        } else {
            ""
        }
    }
    fun checkUsername(username: String): String {
        return if(username.length < 10) {
            "Error! The username is too short!"
        } else {
            ""
        }
    }
    fun checkEmail(email: String): String {
        return if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ""
        } else {
            "Error! Email is invalid!"
        }
    }
    fun checkAge(age: String): String {
        val ageInt = age.toIntOrNull()
        return if(ageInt is Int && ageInt > 0){
            ""
        } else {
            "Error! The age must be positive natural number!"
        }
    }
    fun makeInputsEmpty() {
        binding.etFirstName.setText("")
        binding.etLastName.setText("")
        binding.etEmail.setText("")
        binding.etUsername.setText("")
        binding.etAge.setText("")
        binding.etPhone.setText("")
        binding.error.text = ""
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            var returnOfAgeCheck = checkAge(binding.etAge.text.toString())
            var returnOfEmailCheck = checkEmail(binding.etEmail.text.toString())
            var returnOfUsernameCheck = checkUsername(binding.etUsername.text.toString())
            var returnOfPhoneCheck = checkPhone(binding.etPhone.text.toString())
            var returnOfFilled = isInputEmpty(
                binding.etFirstName.text.toString(),
                binding.etLastName.text.toString(),
                binding.etEmail.text.toString(),
                binding.etUsername.text.toString(),
                binding.etAge.text.toString(),
                binding.etPhone.text.toString()
            )

            binding.error.text = "\n${returnOfAgeCheck} \n${returnOfEmailCheck} \n${returnOfUsernameCheck}" +
                                 "\n${returnOfPhoneCheck} \n${returnOfFilled}"

            var test = binding.error.text.toString()

            if(returnOfAgeCheck == "" && returnOfEmailCheck == "" && returnOfUsernameCheck == ""
                && returnOfPhoneCheck == "" && returnOfFilled == "") {
                val intent = Intent(this, NewActivity::class.java).apply {
                    putExtra("firstName", binding.etFirstName.text.toString())
                    putExtra("lastName", binding.etLastName.text.toString())
                    putExtra("email", binding.etEmail.text.toString())
                    putExtra("username", binding.etUsername.text.toString())
                    putExtra("age", binding.etAge.text.toString())
                    putExtra("phone", binding.etPhone.text.toString())
                }
                startActivity(intent)
            }
        }
        binding.btnClear.setOnLongClickListener{
            makeInputsEmpty()
            true
        }
    }
}