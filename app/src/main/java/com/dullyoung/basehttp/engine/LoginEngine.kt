package com.dullyoung.basehttp.engine

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.dullyoung.basehttp.bean.ResultInfo
import com.dullyoung.basehttp.bean.UserInfo
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


/*
*  Created  in  2021/6/10
*/
class LoginEngine<T> : BaseEngine<T>()  {
    suspend fun login(name: String, pwd: String): ResultInfo<T> {
        val deferred = CoroutineScope(GlobalScope.coroutineContext).async(Dispatchers.IO) {
            val params = HashMap<String, String>()
            params["name"] = name
            params["pwd"] = pwd
            post(
                "url",
                object : TypeToken<ResultInfo<UserInfo>>() {}.type, params
            )
        }
        return deferred.await()
    }
}