package com.dullyoung.basehttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dullyoung.basehttp.bean.ResultInfo
import com.dullyoung.basehttp.bean.ToastCompat
import com.dullyoung.basehttp.bean.UserInfo
import com.dullyoung.basehttp.engine.BaseEngine
import com.dullyoung.basehttp.engine.LoginEngine
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch(Dispatchers.Main) {
            val loginEngine = LoginEngine<UserInfo>()
            loginEngine.login("666", "12345678")
            loginEngine.login("666666", "12345678")
        }
    }
}