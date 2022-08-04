package com.example.practice6_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.practice6_1.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvent()
        setValue()
    }

    override fun setupEvent() {
        signUpBtn.setOnClickListener {
            val inputEmail = emailEdt.text.toString()
            val inputPassword = passwordEdt.text.toString()
            val inputNickname = nicknameEdt.text.toString()

            ServerUtil.putRequestSignUp(inputEmail, inputPassword, inputNickname, object : ServerUtil.Companion.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {
                    val code = jsonObj.getInt("code")
                    if (code == 200) {
                        val message = jsonObj.getString("message")
                        runOnUiThread{
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        val message = jsonObj.getString("message")
                        runOnUiThread{
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }
    }

    override fun setValue() {
    }

}